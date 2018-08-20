package xyz.ieden.quartz.simple.ram.util;

import org.junit.Test;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ieden.quartz.simple.ram.RamJob;
import xyz.ieden.quartz.simple.ram.entity.JobDetailInfo;
import xyz.ieden.quartz.simple.ram.entity.TriggerInfo;

import java.util.Date;

/**
 * @author lianghongwei01
 * @date 2018/8/20 17:41
 */
public class RamUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RamUtilsTest.class);

    @Test
    public void testJob() {

        // 1. 获取 Scheduler
        Scheduler scheduler = RamUtils.getScheduler();

        // 2. 获取 JobDetail
        JobDetailInfo jobDetailInfo = new JobDetailInfo();
        jobDetailInfo.setName("RamJob");
        jobDetailInfo.setGroup("RamGroup");
        JobDetail jobDetail = RamUtils.createJobDetail(RamJob.class, jobDetailInfo);

        // 3. 获取 Trigger
        TriggerInfo triggerInfo = new TriggerInfo();
        triggerInfo.setDescription("This is Trigger.");
        triggerInfo.setName("RamTrigger");
        triggerInfo.setGroup("RamTriggerGroup");
        triggerInfo.setScheduleBuilder(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"));
        Trigger trigger = RamUtils.createTrigger(triggerInfo);

        try {
            Date date = scheduler.scheduleJob(jobDetail, trigger);
            LOGGER.info("注册时间:[{}].", date);

            // 4. 启动
            scheduler.start();
            LOGGER.info("启动时间:{}.", new Date());

            try {
                Thread.sleep(1000 * 60 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
