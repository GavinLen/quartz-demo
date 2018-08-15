package xyz.ieden.quartz.simple.ram;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author lianghongwei01
 * @date 2018/8/15 17:06
 */
public class RamJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(RamJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("RamJob Execute Date:[].", new Date());
    }
}
