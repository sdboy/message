package com.kelan.message.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;

/**
 * <p>动态切换数据源</p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/20 18:53
 * @see
 */
@Order(1)
@Aspect
@Repository
public class DataSourceAspect {
  @Pointcut("execution(* com..dao..*Impl.*(..))")
  private void anyMethod() {
  }

  @AfterReturning(value = "anyMethod()", returning = "result")
  public void afterReturning(JoinPoint joinPoint, Object result){
    DataSourceHolder.clearDataSourceType();
  }

  @Before(value="anyMethod()")
  public void before(JoinPoint joinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();
    //如果方法体上使用了DataSource注解
    if (method.isAnnotationPresent(DataSource.class)) {
      //获取该方法上的注解名
      DataSource datasource = method.getAnnotation(DataSource.class);
      //将方法体上的注解的值赋予给DataSourceHolder数据源持有类
      DataSourceHolder.setDataSourceType(datasource.value());
    }
  }
}
