package kim.aries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaProviderJudgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderJudgeApplication.class, args);
    }

}
