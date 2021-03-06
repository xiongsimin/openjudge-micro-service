package kim.aries.executor;

import kim.aries.consist.BaseConstant;
import kim.aries.executor.impl.JavaCompilerExecutor;
import kim.aries.executor.impl.JavaExecuteExecutor;
import kim.aries.modal.Sample;

/**
 * @Author aries
 * @Data 2021-02-15
 * @Description 进程执行器工厂，生产Java和C++等语言的编译、执行器
 */
public class ExecutorFactory {

    private ExecutorFactory() {

    }

    /**
     * 获取某编程语言指定阶段执行器
     *
     * @param language
     * @param whichStage compile 编译阶段  execute  执行阶段
     * @return
     * @throws Exception
     */
    public static Executor getInstance(String language, String whichStage, String sourceFilePath, String targetFilePath, Sample sample) {
        Executor executor = null;
        switch (language) {
            case "java":
                if (BaseConstant.WHICH_STAGE_COMPILE.equals(whichStage)) {
                    executor = new JavaCompilerExecutor(sourceFilePath);
                } else if (BaseConstant.WHICH_STAGE_EXECUTE.equals(whichStage)) {
                    executor = new JavaExecuteExecutor(targetFilePath, sample);
                }
                break;
            case "c++":
                //TODO 完善C++分支
                break;
            default:
        }
        return executor;
    }
}
