package me.karun;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class Application {

  public static void main(String[] args) {
    final ApplicationContext ctx = SpringApplication.run(Application.class, args);

    log.trace("Beans being loaded: ");

    if (log.isTraceEnabled()) {
      final String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (final String beanName : beanNames) {
        log.trace(beanName);
      }
    }
  }

}
