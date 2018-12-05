package com.kelan.message.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/22 16:54
 * @see
 */
public class HelloWorld implements Job {
  private static int num = 0;

  @Override
  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    num ++;
    System.out.println(num + "----hello world---" + new Date());
  }
}
