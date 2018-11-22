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
  @Rollback(value = false)
  public void addJob() {
    try{
      Class cls = Class.forName("com.kelan.message.job.HelloWorld");
      quartzService.addJob("job2", "jGroup2", "trigger2", "tGroup2",
          cls, "10/5 * * * * ?");
    }catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
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