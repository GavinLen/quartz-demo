package xyz.ieden.quartz.simple.ram;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author lianghongwei01
 * @date 2018/8/20 16:45
 */
public class RamJobTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RamJobTest.class);

    @Test
    public void testExecute() {

        try {
            // 1. 获取调度器实例

            // 创建调度工厂
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            // 通过调度工厂获取调度器
            Scheduler scheduler = schedulerFactory.getScheduler();

            // 2. 创建 Job
            JobDetail jobDetail = JobBuilder.newJob(RamJob.class)
                    // Job 的描述
                    .withDescription("This is xyz.ieden.quartz.simple.ram.RamJob.")
                    // Job 身份:Name 与 group 组合要唯一
                    .withIdentity("ramJob", "RamGroup")
                    .build();

            // 3. 触发器

            // 触发延迟时间
            long currentTime = System.currentTimeMillis() + 3 * 1000L;
            Date startDate = new Date(currentTime);

            Trigger trigger = TriggerBuilder.newTrigger()
                    // Trigger 的描述
                    .withDescription("This is Trigger.")
                    // 触发器身份:Name 与 group 组合要唯一
                    .withIdentity("RamTrigger", "RamTriggerGroup.")
                    // 设置启动时间
                    .startAt(startDate)
                    // 触发器调度类型：CronScheduleBuilder
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();

            // 4. 注册
            Date date = scheduler.scheduleJob(jobDetail, trigger);
            LOGGER.info("注册：{}.", date);

            scheduler.start();
            LOGGER.info("启动时间:{}.", new Date());

            Thread.sleep(1000 * 60 *60);

        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
