package xyz.ieden.quartz.simple.ram;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * This is a RAM Store Quartz!
 *
 * @author dufy
 * @date 2017.02.04
 */
public class RamJobQuartz {

    private static final Logger LOGGER = LoggerFactory.getLogger(RamJobQuartz.class);

    public static void main(String[] args) throws SchedulerException {
        // 1.创建调度工厂
        SchedulerFactory sf = new StdSchedulerFactory();
        // 2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

        //3.创建 JobDetail
        JobDetail jb = JobBuilder.newJob(RamJob.class)
                // job的描述
                .withDescription("this is a xyz.ieden.quartz.simple.ram job")
                // job 的name和group
                .withIdentity("ramJob", "ramGroup")
                .build();

        // 调度延后时间
        long time = System.currentTimeMillis() + 3 * 1000L;
        Date statTime = new Date(time);

        //4.创建触发器
        //使用 SimpleScheduleBuilder 或者 CronScheduleBuilder
        Trigger t = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("ramTrigger", "ramTriggerGroup")
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule())
                //默认当前时间启动
                .startAt(statTime)
                //两秒执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();

        //5.注册任务和定时器
        scheduler.scheduleJob(jb, t);

        //6.启动 调度器
        scheduler.start();
        LOGGER.info("启动时间 ： " + new Date());

    }
}
