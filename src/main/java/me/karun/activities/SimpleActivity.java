package me.karun.activities;

import org.apache.camel.EndpointAware;
import org.apache.camel.Processor;
import org.apache.camel.processor.LogProcessor;
import org.apache.camel.processor.SendProcessor;
import org.apache.camel.processor.interceptor.DefaultChannel;

public class SimpleActivity implements Activity {
  private final String name;

  private SimpleActivity(final String name) {
    this.name = name;
  }

  @Override
  public String generateUml() {
    return name;
  }

  public static SimpleActivity fromEndpoint(final EndpointAware endpointAware) {
    return new SimpleActivity("from " + endpointAware.getEndpoint().toString());
  }

  public static SimpleActivity toEndpoint(final SendProcessor sendProcessor) {
    return new SimpleActivity("to " + sendProcessor.getDestination().toString());
  }

  public static SimpleActivity log(final LogProcessor logProcessor) {
    return new SimpleActivity(logProcessor.getTraceLabel());
  }

  public static SimpleActivity fromChannel(final DefaultChannel processor) {
    return new SimpleActivity("channel >>>>>>>>>>> " + processor.getNextProcessor().toString());
  }

  public static SimpleActivity unknown(final Processor processor) {
    return new SimpleActivity("unknown " + processor.toString());
  }

  @Override
  public String toString() {
    return generateUml();
  }
}
