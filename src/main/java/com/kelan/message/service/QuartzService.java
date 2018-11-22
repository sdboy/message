package com.kelan.message.service;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/22 16:25
 * @see
 */
public interface QuartzService {

  /**
   * 添加一个定时任务
   * @method addJob
   * @author JG
   * @date 2018/11/22 16:28
   * @param jobName 作业名称
   * @param jobGroupName 作业组名称
   * @param triggerName 触发器名称
   * @param triggerGroupName 触发器组名称
   * @param cls 定时任务的class
   * @param cron 时间表达式 void
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void addJob(String jobName, String jobGroupName,String triggerName, String triggerGroupName,
                     Class cls, String cron);
  
  /**
   * 修改定时任务
   * @method modifyJobTime
   * @author JG
   * @date 2018/11/22 16:28
   * @param oldjobName 原job name
   * @param oldjobGroup 原job group
   * @param oldtriggerName 原 trigger name
   * @param oldtriggerGroup 原 trigger group
   * @param jobName 新作业名称
   * @param jobGroup 新作业组名称
   * @param triggerName 新触发器名称
   * @param triggerGroup 新触发器组名称
   * @param cron 新时间表达式
   * @return boolean
   * @since V1.0.0
   * @version V1.0.0
   */
  public boolean modifyJobTime(String oldjobName,String oldjobGroup, String oldtriggerName, String oldtriggerGroup,
                               String jobName, String jobGroup,String triggerName, String triggerGroup, String cron);

  /**
   * 修改触发器调度时间
   * @method modifyJobTime
   * @author JG
   * @date 2018/11/22 16:28
   * @param triggerName 触发器名称
   * @param triggerGroupName 触发器组名称
   * @param cron 新时间表达式
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void modifyJobTime(String triggerName, String triggerGroupName, String cron);

  /**
   * 暂停指定的任务
   * @method pauseJob
   * @author JG
   * @date 2018/11/22 16:28
   * @param jobName 作业名称
   * @param jobGroupName 作业组名称
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void pauseJob(String jobName,String jobGroupName);

  /**
   * 恢复指定的任务
   * @method resumeJob
   * @author JG
   * @date 2018/11/22 16:28
   * @param jobName 作业名称
   * @param jobGroupName 作业组名称
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void resumeJob(String jobName,String jobGroupName);

  /**
   * 删除指定组任务
   * @method removeJob
   * @author JG
   * @date 2018/11/22 16:28
   * @param jobName 作业名称
   * @param jobGroupName 作业组名称
   * @param triggerName 触发器名称
   * @param triggerGroupName 触发器组名称
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName);

  /**
   * 开始所有定时任务，启动调度器
   * @method startSchedule
   * @author JG
   * @date 2018/11/22 16:28
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void startSchedule();

  /**
   * 关闭调度器
   * @method shutdownSchedule
   * @author JG
   * @date 2018/11/22 16:28
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void shutdownSchedule();
}
