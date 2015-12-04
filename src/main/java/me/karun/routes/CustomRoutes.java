package me.karun.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomRoutes extends RouteBuilder {

  @Override
  public void configure() {
    from("mq:queue:input-queue")
      .to("file:///Users/karun/workspace/camel-spike/output");
  }
}
