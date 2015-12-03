package me.karun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    final Logger logger = LoggerFactory.getLogger(Application.class);
    final ApplicationContext ctx = SpringApplication.run(Application.class, args);

    logger.info("Let's inspect the beans provided by Spring Boot:");

    final String[] beanNames = ctx.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (final String beanName : beanNames) {
      logger.info(beanName);
    }
  }

}
