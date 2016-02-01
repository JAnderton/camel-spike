package me.karun.activities;

import org.apache.camel.EndpointAware;
import org.apache.camel.Processor;
import org.apache.camel.processor.ChoiceProcessor;
import org.apache.camel.processor.LogProcessor;
import org.apache.camel.processor.SendProcessor;
import org.apache.camel.processor.interceptor.DefaultChannel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static me.karun.activities.ConditionalActivity.when;
import static me.karun.activities.SimpleActivity.*;

public class ActivityDiagram implements UmlElement {
  private List<Activity> activities = new ArrayList<>();

  private ActivityDiagram() {
  }

  @Override
  public String generateUml() {
    return activities.stream()
      .map(Activity::generateUml)
      .collect(joining(lineSeparator()));
  }

  public static ActivityDiagram draw() {
    return new ActivityDiagram();
  }

  public ActivityDiagram addElement(final EndpointAware endpointAware) {
    activities.add(fromEndpoint(endpointAware));

    return this;
  }

  public ActivityDiagram addElement(final Processor p) {
    if (p instanceof DefaultChannel) {
      return addElement((DefaultChannel) p);
    }

    activities.add(unknown(p));
    return this;
  }

  public ActivityDiagram addElement(final DefaultChannel p) {
    final Processor next = p.getNextProcessor();
    if (next instanceof ChoiceProcessor) {
      activities.add(when((ChoiceProcessor) next));
    } else if (next instanceof SendProcessor) {
      activities.add(toEndpoint(((SendProcessor) next)));
    } else if (next instanceof LogProcessor) {
      activities.add(log((LogProcessor) next));
    } else {
      activities.add(fromChannel(p));
    }
    return this;
  }
}
