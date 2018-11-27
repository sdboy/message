package com.kelan.message.configuration;

import com.kelan.message.listener.FaceMessageListener;
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
  @Value(value = "${FACE.url}")
  private String url;
  /**
   * 消费者id
   */
  @Value(value = "${FACE.clientId}")
  private String clientId;
  /**
   * 会话数量
   */
  @Value(value = "${FACE.sessionCacheSize}")
  private int sessionCacheSize;
  /**
   * 主题名
   */
  @Value(value = "${FACE.topic1}")
  private String topicId1;
  /**
   * 主题名
   */
  @Value(value = "${FACE.topic2}")
  private String topicId2;
  /**
   * 重连间隔时间，单位毫秒
   */
  @Value(value = "${FACE.interval}")
  private long interval;
  /**
   * 最大重连次数
   */
  @Value(value = "${FACE.maxAttempts}")
  private long maxAttempts;
  /**
   * 接受消息超时，单位毫秒
   */
  @Value(value = "${FACE.receiveTimeout}")
  private long receiveTimeout;
  /**
   * 是否持久化，true为是，false为否
   */
  @Value(value = "${FACE.subscriptionDurable}")
  private boolean subscriptionDurable;
  /**
   * 持久化名称
   */
  @Value(value = "${FACE.durableSubscriptionName}")
  private String durableSubscriptionName;

  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
    mqConnectionFactory.setBrokerURL(url);
    return mqConnectionFactory;
  }

  @Bean
  public CachingConnectionFactory cachingConnectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setSessionCacheSize(sessionCacheSize);
    connectionFactory.setTargetConnectionFactory(connectionFactory());
    connectionFactory.setClientId(clientId);
    return connectionFactory;
  }

  @Bean
  public ActiveMQTopic topic1() {
    ActiveMQTopic mqTopic = new ActiveMQTopic();
    mqTopic.setPhysicalName(topicId1);
    return mqTopic;
  }

  @Bean
  public ActiveMQTopic topic2() {
    ActiveMQTopic mqTopic = new ActiveMQTopic();
    mqTopic.setPhysicalName(topicId2);
    return mqTopic;
  }

  @Bean
  public FaceMessageListener faceMessageListener() {
    return new FaceMessageListener();
  }

  @Bean
  public MessageListenerAdapter messageListenerAdapter() {
    MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();
    messageListenerAdapter.setDelegate(faceMessageListener());
    messageListenerAdapter.setDefaultListenerMethod("onMessage");
    return messageListenerAdapter;
  }

  @Bean
  public FixedBackOff fixedBackOff() {
    FixedBackOff fixedBackOff = new FixedBackOff();
    fixedBackOff.setInterval(interval);
    fixedBackOff.setMaxAttempts(maxAttempts);
    return fixedBackOff;
  }

  @Bean
  public DefaultMessageListenerContainer topicMessageListenerAdapterContainer1() {
    DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
    listenerContainer.setConnectionFactory(cachingConnectionFactory());
    listenerContainer.setDestination(topic1());
    listenerContainer.setMessageListener(messageListenerAdapter());
    listenerContainer.setPubSubDomain(Boolean.TRUE);
    listenerContainer.setBackOff(fixedBackOff());
    listenerContainer.setClientId(clientId);
    listenerContainer.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
    listenerContainer.setReceiveTimeout(receiveTimeout);
    if(subscriptionDurable && StringUtils.isNotBlank(durableSubscriptionName)) {
      listenerContainer.setSubscriptionDurable(subscriptionDurable);
      listenerContainer.setDurableSubscriptionName(durableSubscriptionName);
    }
    return listenerContainer;
  }

  @Bean
  public DefaultMessageListenerContainer topicMessageListenerAdapterContainer2() {
    DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
    listenerContainer.setConnectionFactory(cachingConnectionFactory());
    listenerContainer.setDestination(topic2());
    listenerContainer.setMessageListener(messageListenerAdapter());
    listenerContainer.setPubSubDomain(Boolean.TRUE);
    listenerContainer.setBackOff(fixedBackOff());
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
