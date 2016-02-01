package me.karun.activities;

import org.apache.camel.processor.CamelLogProcessor;
import org.apache.camel.processor.FilterProcessor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static me.karun.activities.FilterActivity.filter;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FilterActivityTest {

  @Ignore
  @Test
  public void shouldGenerateUml() {
    final FilterProcessor filterProcessor = new FilterProcessor(exchange -> false, new CamelLogProcessor());

    assertThat(filter(filterProcessor).generateUml()).isEqualTo("from task");
  }

}
