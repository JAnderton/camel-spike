package me.karun.activities;

import org.apache.camel.Processor;
import org.apache.camel.processor.FilterProcessor;

import static me.karun.activities.SimpleActivity.unknown;

public class FilterActivity implements Activity {

  private final String condition;
  private final SimpleActivity action;

  private FilterActivity(final String condition, final SimpleActivity action) {
    this.condition = condition;
    this.action = action;
  }

  private FilterActivity(final Processor p) {
    this(((FilterProcessor) p).getPredicate().toString(), unknown(((FilterProcessor) p).getProcessor()));
  }

  public static FilterActivity filter(final Processor p) {
    return new FilterActivity(p);
  }

  @Override
  public String generateUml() {
    return "if (someCondition) { " + action + " }";
//    return "if (" + condition + ") { " + action + " }";
  }


  @Override
  public String toString() {
    return generateUml();
  }
}
