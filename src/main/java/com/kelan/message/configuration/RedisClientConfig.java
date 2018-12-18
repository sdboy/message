package com.kelan.message.configuration;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
/**
 * 不使用就需要注解掉，如果配置了启用注解注释，就必须要配置cacheManager 否则就会启动报错。
 * 因为配置了这个缓存拦截器就会去注入cacheManager
 */
@EnableCaching

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/12/14 15:49
 * @see
 */
public class RedisClientConfig {

  @Value(value = "${redis.maxIdle}")
  private int maxIdle;
  @Value(value = "${redis.maxTotal}")
  private int maxTotal;
  @Value(value = "${redis.minIdle}")
  private int minIdle;
  @Value(value = "${redis.hostname}")
  private String hostName;
  @Value(value = "${redis.port}")
  private int port;
  @Value(value = "${redis.password}")
  private String password;

  /**
   * <p>genericObjectPoolConfig common-pool2线程池</p>
   * @method genericObjectPoolConfig
   * @author JG
   * @date 2018/12/14 16:10
   * @return org.apache.commons.pool2.impl.GenericObjectPoolConfig
   * @since V1.0.0
   * @version V1.0.0
   */
  @Bean
  public GenericObjectPoolConfig genericObjectPoolConfig() {
    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    poolConfig.setMaxIdle(maxIdle);
    poolConfig.setMaxTotal(maxTotal);
    poolConfig.setMinIdle(minIdle);
    return poolConfig;
  }

  /**
   * <p>配置LettuceClientConfiguration 包括线程池配置和安全项配置</p>
   * @method lettuceClientConfiguration
   * @author JG
   * @date 2018/12/14 16:09
   * @return org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
   * @since V1.0.0
   * @version V1.0.0
   */
  @Bean
  public LettuceClientConfiguration lettuceClientConfiguration() {
    return LettucePoolingClientConfiguration.builder().poolConfig(genericObjectPoolConfig()).build();
  }

  /**
   * <p>配置redis连接信息</p>
   * @method redisStandaloneConfiguration
   * @author JG
   * @date 2018/12/14 16:11
   * @return org.springframework.data.redis.connection.RedisStandaloneConfiguration
   * @since V1.0.0
   * @version V1.0.0
   */
  @Bean
  public RedisStandaloneConfiguration redisStandaloneConfiguration() {
    RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
    standaloneConfiguration.setHostName(hostName);
    standaloneConfiguration.setPort(port);
    standaloneConfiguration.setPassword(password);
    return standaloneConfiguration;
  }

  /**
   * <p>配置连接工厂</p>
   * @method lettuceConnectionFactory
   * @author JG
   * @date 2018/12/14 16:11
   * @return org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
   * @since V1.0.0
   * @version V1.0.0
   */
  @Bean
  public LettuceConnectionFactory lettuceConnectionFactory() {
    return new LettuceConnectionFactory(redisStandaloneConfiguration(), lettuceClientConfiguration());
  }

  /**
   * <p>以字符串序列化</p>
   * @method stringRedisSerializer
   * @author JG
   * @date 2018/12/14 17:28
   * @return org.springframework.data.redis.serializer.StringRedisSerializer
   * @since V1.0.0
   * @version V1.0.0
   */
  @Bean
  public StringRedisSerializer stringRedisSerializer() {
    return new StringRedisSerializer();
  }

  /**
   * <p>json序列化</p>
   * @method genericFastJsonRedisSerializer
   * @author JG
   * @date 2018/12/14 17:29
   * @return com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer
   * @since V1.0.0
   * @version V1.0.0
   */
  @Bean
  public GenericFastJsonRedisSerializer genericFastJsonRedisSerializer() {
    return new GenericFastJsonRedisSerializer();
  }

  /**
   * <p>配置redis模板</p>
   * @method redisTemplate
   * @author JG
   * @date 2018/12/18 10:41
   * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
   * @since V1.0.0
   * @version V1.0.0
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setEnableTransactionSupport(true);
    redisTemplate.setKeySerializer(stringRedisSerializer());
    redisTemplate.setValueSerializer(genericFastJsonRedisSerializer());
    redisTemplate.setHashKeySerializer(stringRedisSerializer());
    redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer());
    redisTemplate.setConnectionFactory(lettuceConnectionFactory());
    return redisTemplate;
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate() {
    StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
    stringRedisTemplate.setConnectionFactory(lettuceConnectionFactory());
    stringRedisTemplate.setEnableTransactionSupport(true);
    return stringRedisTemplate;
  }

  /**
   * <p>配置cacheManager</p>
   * @method cacheManager
   * @author JG
   * @date 2018/12/18 10:42
   * @return org.springframework.cache.CacheManager
   * @since V1.0.0
   * @version V1.0.0
   */
  @Bean
  public CacheManager cacheManager() {
    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().
        entryTtl(Duration.ofHours(1));

    return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(
            lettuceConnectionFactory())).cacheDefaults(redisCacheConfiguration).build();
  }

}
