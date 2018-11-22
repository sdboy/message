package com.kelan.message.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/22 13:44
 * @see
 */
public class CustomJobFactory extends SpringBeanJobFactory {

  @Autowired
  private AutowireCapableBeanFactory capableBeanFactory;

  @Override
  protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
    //调用父类的方法
    Object jobInstance = super.createJobInstance(bundle);
    //进行注入
    capableBeanFactory.autowireBean(jobInstance);
    return jobInstance;
  }
}
