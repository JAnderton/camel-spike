package me.karun.activities;

import org.apache.camel.processor.ChoiceProcessor;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class ConditionalActivity implements Activity {

  private final List<FilterActivity> filters;

  public ConditionalActivity() {
    this.filters = new ArrayList<>();
  }

  @Override
  public String generateUml() {
    return filters.stream()
      .map(FilterActivity::generateUml)
      .collect(joining(lineSeparator()));
  }

  public static ConditionalActivity when(final ChoiceProcessor choice) {
    final ConditionalActivity when = new ConditionalActivity();

    choice.getFilters().stream()
      .map(FilterActivity::filter)
      .forEach(when::add);
    return when;
  }

  private void add(final FilterActivity f) {
    filters.add(f);
  }

  @Override
  public String toString() {
    return filters.toString();
  }
}
