package com.kelan.message.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml","classpath:spring/spring-servlet.xml"})
@Transactional(transactionManager = "transactionManager")
@Rollback(value = true)

public class RedisServiceTest {

  @Autowired
  private RedisService redisService;

  @Ignore
  @Rollback(value = false)
  public void operateRedis() {
    List<String> list = new ArrayList<>();
    list.add("c");
    list.add("c++");
    list.add("java");
    list.add("javascript");
    list.add("python");
    redisService.operateRedis("language", list);
  }

  @Ignore
  public void getValueList() {
    List<Object> objectList = redisService.getValueList("language");
    for(Object object: objectList) {
      System.out.println(object.toString());
    }
  }
}