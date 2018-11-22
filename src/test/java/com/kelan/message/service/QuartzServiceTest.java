package com.kelan.message.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml","classpath:spring/spring-servlet.xml"})
@Transactional(transactionManager = "transactionManager")
@Rollback(value = true)
public class QuartzServiceTest {

  @Autowired
  private QuartzService quartzService;

  @Test
  public void addJob() {
  }

  @Ignore
  public void modifyJobTime() {
  }

  @Ignore
  public void modifyJobTime1() {
  }

  @Ignore
  public void pauseJob() {
  }

  @Ignore
  public void resumeJob() {
  }

  @Ignore
  public void removeJob() {
  }

  @Ignore
  public void startSchedule() {
  }

  @Ignore
  public void shutdownSchedule() {
  }
}