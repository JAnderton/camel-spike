package me.karun.config;

import me.karun.routes.CustomRoutes;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class AppConfig {

  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
    return new ActiveMQConnectionFactory(
      ActiveMQConnection.DEFAULT_USER,
      ActiveMQConnection.DEFAULT_USER,
      ActiveMQConnection.DEFAULT_BROKER_URL
    );
  }

  @Bean
  public PooledConnectionFactory pooledConnectionFactoryBean(final ConnectionFactory connectionFactory) {
    final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
    pooledConnectionFactory.setConnectionFactory(connectionFactory);
    pooledConnectionFactory.setMaxConnections(1);

    return pooledConnectionFactory;
  }

  @Bean
  public JmsConfiguration jmsConfiguration(final PooledConnectionFactory pooledConnectionFactory) {
    final JmsConfiguration jmsConfiguration = new JmsConfiguration();
    jmsConfiguration.setConnectionFactory(pooledConnectionFactory);
    jmsConfiguration.setConcurrentConsumers(1);

    return jmsConfiguration;
  }

  @Bean
  public ActiveMQComponent activeMQComponent(final JmsConfiguration jmsConfiguration) {
    final ActiveMQComponent activeMQComponent = new ActiveMQComponent();
    activeMQComponent.setConfiguration(jmsConfiguration);
//    activeMQComponent.setTransacted(true);
//    activeMQComponent.setCacheLevelName("CACHE_CONSUMER");

    return activeMQComponent;
  }

  @Bean
  public CamelContext camelContext(final PooledConnectionFactory pooledConnectionFactory) throws Exception {
    final SpringCamelContext camelContext = new SpringCamelContext();

    camelContext.addRoutes(new CustomRoutes());
    camelContext.startAllRoutes();

    camelContext.addComponent("mq", JmsComponent.jmsComponentTransacted(pooledConnectionFactory));

    return camelContext;
  }
}
