package me.karun.config;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  CamelContextConfiguration contextConfiguration() {
    return camelContext -> {
      final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
        ActiveMQConnection.DEFAULT_USER,
        ActiveMQConnection.DEFAULT_USER,
        ActiveMQConnection.DEFAULT_BROKER_URL
      );

      final RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
      redeliveryPolicy.setMaximumRedeliveries(RedeliveryPolicy.NO_MAXIMUM_REDELIVERIES);
      activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);

      final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
      pooledConnectionFactory.setMaxConnections(2);
      pooledConnectionFactory.setMaximumActiveSessionPerConnection(2);

      camelContext.addComponent("mq", JmsComponent.jmsComponentTransacted(pooledConnectionFactory));
      camelContext.getShutdownStrategy().setTimeout(2);
    };
  }
}
