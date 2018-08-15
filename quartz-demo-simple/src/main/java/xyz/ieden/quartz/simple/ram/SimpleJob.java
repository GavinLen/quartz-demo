package xyz.ieden.quartz.simple.ram;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author gavin
 * @date 2018/8/15 23:37
 */
public class SimpleJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleJob.class);

    public SimpleJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("Simple Job:[{}].", new Date());
    }
}
