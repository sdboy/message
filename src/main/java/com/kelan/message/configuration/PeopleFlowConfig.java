package com.kelan.message.configuration;

import com.kelan.message.listener.PeopleFlowMessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.util.backoff.FixedBackOff;

import javax.jms.Session;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/26 14:47
 * @see
 */
@Configuration
public class PeopleFlowConfig {

  /**
   * mq地址
   */
  @Value(value = "${PEOPLE.url}")
  private String url;
  /**
   * 消费者id
   */
  @Value(value = "${PEOPLE.clientId}")
  private String clientId;
  /**
   * 会话数量
   */
  @Value(value = "${PEOPLE.sessionCacheSize}")
  private int sessionCacheSize;
  /**
   * 主题名
   */
  @Value(value = "${PEOPLE.topic}")
  private String topic;
  /**
   * 重连间隔时间，单位毫秒
   */
  @Value(value = "${PEOPLE.interval}")
  private long interval;
  /**
   * 最大重连次数
   */
  @Value(value = "${PEOPLE.maxAttempts}")
  private long maxAttempts;
  /**
   * 接受消息超时，单位毫秒
   */
  @Value(value = "${PEOPLE.receiveTimeout}")
  private long receiveTimeout;
  /**
   * 是否持久化，true为是，false为否
   */
  @Value(value = "${PEOPLE.subscriptionDurable}")
  private boolean subscriptionDurable;
  /**
   * 持久化名称
   */
  @Value(value = "${PEOPLE.durableSubscriptionName}")
  private String durableSubscriptionName;

  @Bean
  public ActiveMQConnectionFactory peopleFlowConnectionFactory() {
    ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
    mqConnectionFactory.setBrokerURL(url);
    return mqConnectionFactory;
  }

  @Bean
  public CachingConnectionFactory peopleFlowCachingConnectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setSessionCacheSize(sessionCacheSize);
    connectionFactory.setTargetConnectionFactory(peopleFlowConnectionFactory());
    connectionFactory.setClientId(clientId);
    return connectionFactory;
  }

  @Bean
  public ActiveMQTopic peopleFlowCTopic() {
    ActiveMQTopic mqTopic = new ActiveMQTopic();
    mqTopic.setPhysicalName(topic);
    return mqTopic;
  }

  @Bean
  public PeopleFlowMessageListener peopleFlowMessageListener() {
    return new PeopleFlowMessageListener();
  }

  @Bean
  public MessageListenerAdapter peopleFlowMessageListenerAdapter() {
    MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();
    messageListenerAdapter.setDelegate(peopleFlowMessageListener());
    messageListenerAdapter.setDefaultListenerMethod("onMessage");
    return messageListenerAdapter;
  }

  @Bean
  public FixedBackOff peopleFlowCFixedBackOff() {
    FixedBackOff fixedBackOff = new FixedBackOff();
    fixedBackOff.setInterval(interval);
    fixedBackOff.setMaxAttempts(maxAttempts);
    return fixedBackOff;
  }

  @Bean
  public DefaultMessageListenerContainer topicPeopleMessageListenerAdapterContainer() {
    DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
    listenerContainer.setConnectionFactory(peopleFlowCachingConnectionFactory());
    listenerContainer.setDestination(peopleFlowCTopic());
    listenerContainer.setMessageListener(peopleFlowMessageListenerAdapter());
    listenerContainer.setPubSubDomain(Boolean.TRUE);
    listenerContainer.setBackOff(peopleFlowCFixedBackOff());
    listenerContainer.setClientId(clientId);
    listenerContainer.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
    listenerContainer.setReceiveTimeout(receiveTimeout);
    if(subscriptionDurable && StringUtils.isNotBlank(durableSubscriptionName)) {
      listenerContainer.setSubscriptionDurable(subscriptionDurable);
      listenerContainer.setDurableSubscriptionName(durableSubscriptionName);
    }
    return listenerContainer;
  }
}
