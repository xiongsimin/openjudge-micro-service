package kim.aries.executor.impl;

import kim.aries.consist.BaseConstant;
import kim.aries.executor.Executor;
import kim.aries.modal.ExecutorResult;
import kim.aries.utils.ProcessUtil;

import java.io.File;
import java.util.concurrent.Callable;


/**
 * @Author aries
 * @Data 2021-02-15
 */
public class JavaCompilerExecutor implements Executor, Callable {

    private String sourceFilePath;
    private ExecutorResult executorResult = new ExecutorResult();

    public JavaCompilerExecutor(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public ExecutorResult getExecutorResult() {
        return executorResult;
    }

    /**
     * 执行java编译程序
     *
     * @return
     */
    @Override
    public ExecutorResult service() {
        Runtime rt = Runtime.getRuntime();
        Process p1 = null;
        String[] command1 = {BaseConstant.JDK_PATH + "\\javac.exe", "-encoding", "utf-8", sourceFilePath + File.separator + BaseConstant.JAVA_SOURCE_FILE_NAME};
        try {
            p1 = rt.exec(command1);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("执行javac命令出错！");
        }
        ProcessUtil.getSystemOut(p1, executorResult);
        return executorResult;
    }


    @Override
    public Object call() throws Exception {
        return service();
    }
}
