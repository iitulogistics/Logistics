package kz.logistic.pl.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS");
      //.allowedMethods(String.valueOf(Arrays.asList("GET", "POST", "PATCH", "DELETE", "PUT", "OPTIONS")));
  }

}
