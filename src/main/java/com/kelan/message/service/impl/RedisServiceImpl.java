package com.kelan.message.service.impl;

import com.kelan.message.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/12/18 11:28
 * @see
 */
@Service
public class RedisServiceImpl implements RedisService {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Override
  public void operateRedis(String key, List<String> valueList) {
    for(String value: valueList) {
      redisTemplate.opsForList().leftPush(key, value);
    }
  }

  @Override
  public List<Object> getValueList(String key) {
    List<Object> stringList = redisTemplate.opsForList().range(key, 0, -1);
    return stringList;
  }
}
