package me.karun.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomRoutes extends RouteBuilder {

  @Override
  public void configure() {
    from("mq:queue:in")
      .log("Message received")
      .to("mq:queue:out");
  }
}
