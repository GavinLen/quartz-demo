package xyz.ieden.quartz.simple.ram.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import xyz.ieden.quartz.simple.ram.entity.JobDetailInfo;
import xyz.ieden.quartz.simple.ram.entity.TriggerInfo;

/**
 * @author lianghongwei01
 * @date 2018/8/20 16:51
 */
public class RamUtils {

    private static SchedulerFactory schedulerFactory;

    static {
        getSchedulerFactory();
    }

    private static void getSchedulerFactory() {
        schedulerFactory = new StdSchedulerFactory();
    }

    /**
     * 创建 Trigger
     *
     * @param info
     * @return
     */
    public static Trigger createTrigger(TriggerInfo info) {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription(info.getDescription())
                .withIdentity(info.getName(), info.getGroup())
                .withSchedule(info.getScheduleBuilder())
                .build();
        return trigger;
    }

    /**
     * 创建 JobDetail
     *
     * @param clazz
     * @param info
     * @return
     */
    public static JobDetail createJobDetail(Class clazz, JobDetailInfo info) {
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withDescription(info.getDescription())
                .withIdentity(info.getName(), info.getGroup())
                .build();
        return jobDetail;
    }

    /**
     * 获取调度
     *
     * @return
     */
    public static Scheduler getScheduler() {
        Scheduler scheduler = null;
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }

}
