package kim.aries.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author aries
 * @Data 2021-02-18
 */
@Configuration
public class ExecuteTaskThreadPool {
    @Bean
    ThreadPoolExecutor myExecuteTaskThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 8, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }
}
