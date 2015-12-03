package me.karun.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomRoutes extends SpringRouteBuilder {

  @Override
  public void configure() {
    from("direct:start").to("mock:result");
  }
}
