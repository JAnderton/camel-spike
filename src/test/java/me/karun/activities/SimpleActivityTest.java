package me.karun.activities;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointAware;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static me.karun.activities.SimpleActivity.fromEndpoint;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleActivityTest {
  @Test
  public void shouldGenerateUmlGenerates() {
    final EndpointAware endpointAware = mock(EndpointAware.class);
    final Endpoint endpoint = mock(Endpoint.class);

    when(endpointAware.getEndpoint()).thenReturn(endpoint);
    when(endpoint.toString()).thenReturn("task");

    assertThat(fromEndpoint(endpointAware).generateUml()).isEqualTo("from task");
  }
}
