package me.karun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class Application {
  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    final ApplicationContext ctx = SpringApplication.run(Application.class, args);

    logger.trace("Beans being loaded: ");

    if (logger.isTraceEnabled()) {
      final String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (final String beanName : beanNames) {
        logger.trace(beanName);
      }
    }
  }

}
