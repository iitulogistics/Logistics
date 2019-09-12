package kz.logistic.pl;

import kz.logistic.pl.configs.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@Import(SwaggerConfig.class)
@SpringBootApplication
@EnableEurekaClient
public class PlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlApplication.class, args);
    }
}
