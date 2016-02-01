package me.karun.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ComplexRoute extends RouteBuilder {

  @Override
  public void configure() {
//    from("mq:queue:in")
//      .choice()
//      .when(ex -> isNotBlank(ex.getIn().getBody().toString()))
//      .log("Message is not blank")
//      .otherwise()
//      .log("Message is blank")
//      .end()
//      .to("mq:queue:out");

//    from("direct:a")
//      .filter(header("foo").isEqualTo("bar"))
//      .to("direct:b");
//
//    from("direct:c")
//      .choice()
//        .when(header("foo").isEqualTo("bar"))
//          .to("direct:d")
//        .when(header("foo").isEqualTo("cheese"))
//          .to("direct:e")
//        .otherwise()
//          .to("direct:f");
  }
}
