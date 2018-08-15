package xyz.ieden.quartz.simple.ram;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author gavin
 * @date 2018/8/15 23:17
 */
public class SimpleTriggerExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTriggerExample.class);

    private static final String JOB_NAME = "simpleJob";
    private static final String JOB_GROUP = "SimpleJob_Group";
    private static final String TRIGGER_NAME = "Simple_Trigger";
    private static final String TRIGGER_GROUP = "Simple_Trigger_Group";

    public static void main(String[] args) throws SchedulerException {
        SimpleTriggerExample example = new SimpleTriggerExample();

        example.register(JOB_NAME, JOB_GROUP, TRIGGER_NAME, TRIGGER_GROUP, 3 * 1000);

        example.start();
    }

    public void start() {

    }

    /**
     * 注册触发器
     *
     * @param jobName      Job 名称
     * @param jobGroup     Job 组名称
     * @param triggerName  触发器名称
     * @param triggerGroup 触发器组名称
     * @param afterTime    之后时间触发(单位:ms)
     */
    public void register(String jobName, String jobGroup, String triggerName, String triggerGroup, long afterTime) throws SchedulerException {

        LOGGER.info("Scheduler Initializing start.");
        // 1. 初始化调度器工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        // 2. 初始化调度器
        Scheduler scheduler = schedulerFactory.getScheduler();
        LOGGER.info("Scheduler Initializing End.");

        // 3. 获取当执行时间
        long time = System.currentTimeMillis() + afterTime;
        Date runTime = new Date(time);

        LOGGER.info("Scheduling Job Start.");
        // 4. 定义 Job，在 Quartz 中，jobGroup+jobName 必须唯一
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity(jobName, jobGroup).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup).withSchedule(SimpleScheduleBuilder.simpleSchedule()).startAt(runTime).build();
        scheduler.scheduleJob(jobDetail, trigger);
        LOGGER.info("Scheduling Job End.");

        LOGGER.info("Job [{}] Run [{}].", jobDetail.getKey(), runTime);
    }

}
