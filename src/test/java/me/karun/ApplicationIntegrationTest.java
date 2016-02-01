package me.karun;

import lombok.extern.slf4j.Slf4j;
import me.karun.activities.ActivityDiagram;
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static me.karun.activities.ActivityDiagram.draw;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationIntegrationTest {
  @Autowired
  private CamelContext context;

  @Test
  public void shouldGetCamelRoute() {
    final List<String> expectedData = asList(
      // TODO: Fix first expectation
//      "from Endpoint[mq://queue:in]\nif (someCondition) { unknown Channel[Log(route1)[Message is not blank]] }\nto Endpoint[mq://queue:out]",
      "from Endpoint[mq://queue:in]\nlog[Message received]\nto Endpoint[mq://queue:out]"
    );

    final List<String> actualData = parseRoutes(context.getRoutes()).stream()
      .map(ActivityDiagram::generateUml)
      .collect(toList());

    assertThat("Didn't get expected number of routes", actualData.size(), is(expectedData.size()));
    for (int i = 0; i < actualData.size(); i++) {
      assertThat(actualData.get(i), is(expectedData.get(i)));
    }
  }

  private static List<ActivityDiagram> parseRoutes(final List<Route> routes) {
    final List<ActivityDiagram> parsedRoutes = new ArrayList<>();

    for (final Route route : routes) {
      final ActivityDiagram diagram = draw();
      diagram.addElement(route.getConsumer());

      route.navigate().next().forEach(diagram::addElement);

      parsedRoutes.add(diagram);
    }

    return parsedRoutes;
  }
}
