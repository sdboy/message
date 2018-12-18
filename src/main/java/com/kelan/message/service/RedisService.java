package com.kelan.message.service;

import java.util.List;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/12/18 11:27
 * @see
 */
public interface RedisService {
  /**
   * <p>redis操作测试</p>
   * @method operateRedis
   * @author JG
   * @date 2018/12/18 11:28
   * @param key String 存储的key
   * @param valueList 值列表
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void operateRedis(String key, List<String> valueList);

  /**
   * 获取redis中的值
   * @method getValueList
   * @author JG
   * @date 2018/12/18 16:14
   * @param key 存储的key
   * @return java.util.List<java.lang.Object>
   * @since V1.0.0
   * @version V1.0.0
   */
  public List<Object> getValueList(String key);
}
