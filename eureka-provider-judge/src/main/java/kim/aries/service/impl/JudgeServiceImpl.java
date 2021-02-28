package kim.aries.service.impl;

import kim.aries.consist.BaseConstant;
import kim.aries.executor.Executor;
import kim.aries.executor.ExecutorFactory;
import kim.aries.modal.ExecutorResult;
import kim.aries.modal.JudgeRequest;
import kim.aries.modal.JudgeResult;
import kim.aries.modal.Sample;
import kim.aries.service.JudgeService;
import kim.aries.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @Author aries
 * @Data 2021-02-28
 */
@Service("judgeService")
public class JudgeServiceImpl implements JudgeService {

    @Autowired
    ThreadPoolExecutor myExecuteTaskThreadPool;

    @Override
    public JudgeResult doJudge(JudgeRequest judgeRequest) throws IOException, InterruptedException {
        //编程语言，如“java”
        String type = judgeRequest.getLanguage();
        String userId = UUID.randomUUID().toString();
        List<Sample> sampleList = judgeRequest.getSamples();
        JudgeResult result = new JudgeResult();
        List<Future<ExecutorResult>> futureList = new ArrayList<>();
        Date date = new Date();
        Long time = date.getTime();//获取系统当前时间
        String sourceFilePath = BaseConstant.SOURCE_FILE_ROOT_PATH + File.separator + userId + File.separator + time;
        //将代码写入源文件
        FileUtil.writeToFile(judgeRequest.getCode().getBytes(), sourceFilePath, BaseConstant.JAVA_SOURCE_FILE_NAME);
        Executor compileExecutor = ExecutorFactory.getInstance(type, BaseConstant.WHICH_STAGE_COMPILE, sourceFilePath, null, null);
        Future<ExecutorResult> compileFuture = null;
        try {
            compileFuture = myExecuteTaskThreadPool.submit((Callable<ExecutorResult>) compileExecutor);
        } catch (RejectedExecutionException e) {
            result.setState(BaseConstant.JUDGE_RESULT_STATE_SYSTEM_BUSY);
            e.printStackTrace();
            return result;
        }

//        Future<ExecutorResult> compileFuture = myExecuteTaskThreadPool.submit((Callable<ExecutorResult>) compileExecutor);
        ExecutorResult compilerResult = null;
        try {
            compilerResult = compileFuture.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //编译期错误
        if (StringUtils.isNotBlank(compilerResult.getErrorMsg())) {
            result.setState(BaseConstant.JUDGE_RESULT_STATE_COMPILE_ERROR);
            result.setCompileErrorMsg(compilerResult.getErrorMsg());
        }
        //编译通过
        else {
            //失败用例
            List<Sample> failedSample = new ArrayList<>();
            //用例线程数
            int sampleThreadCount = sampleList.size();
//            CountDownLatch cd = new CountDownLatch(sampleThreadCount);
            //循环遍历测试用例
            for (Sample sample : sampleList) {
                System.out.println("线程“" + Thread.currentThread().getName() + "”开始执行");
                //执行二进制字节码，得到结果
                Executor executeExecutor = ExecutorFactory.getInstance(type, BaseConstant.WHICH_STAGE_EXECUTE, sourceFilePath, sourceFilePath, sample);
//                myExecuteTaskThreadPool.submit((Runnable) executeExecutor);
                ExecutorResult executeResult = null;
                try {
                    Future<ExecutorResult> executeFuture = myExecuteTaskThreadPool.submit((Callable<ExecutorResult>) executeExecutor);
                    futureList.add(executeFuture);
                } catch (RejectedExecutionException e) {
                    result.setState(BaseConstant.JUDGE_RESULT_STATE_SYSTEM_BUSY);
                    e.printStackTrace();
                    return result;
                }
            }
            for (Future<ExecutorResult> future : futureList) {
                ExecutorResult executorResult = null;
                try {
                    executorResult = future.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Sample sample = executorResult.getSample();
                //结果与测试用例标准答案对比
                //运行时错误
                if (StringUtils.isNotBlank(executorResult.getErrorMsg())) {
                    result.setState(BaseConstant.JUDGE_RESULT_STATE_RUNTIME_ERROR);
                } else {
                    //用户程序输出
                    String userOutput = executorResult.getResult();
                    //正确答案与输出不相同
                    if (!sample.getOutput().equals(userOutput)) {
                        result.setState(BaseConstant.JUDGE_RESULT_STATE_WRONG_ANSWER);
                        sample.setUserOutput(userOutput);
                        failedSample.add(sample);
                    }
                }
            }
            //等待所有线程用例全部执行完毕
//            cd.await();
            System.out.println("用例线程全部执行完毕");
            result.setFailedSamples(failedSample);
            result.setAcceptRate((float) (sampleList.size() - failedSample.size()) / sampleList.size());
            if (failedSample.size() == 0) {
                result.setState(BaseConstant.JUDGE_RESULT_STATE_ACCEPT);
            }
        }
        return result;
    }
}
