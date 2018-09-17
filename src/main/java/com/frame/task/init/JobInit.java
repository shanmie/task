package com.frame.task.init;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Package: com.frame.task.init
 * @ClassName: Trigger
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/9/11 上午11:52
 * @Version: 1.0
 */
@Slf4j
public class JobInit {

    Scheduler scheduler;

    public static void main(String[] args) throws SchedulerException {
        JobInit ji = new JobInit();
        ji.instance();
    }


    public void instance() throws SchedulerException {
        init();
    }

    private void init() throws SchedulerException {

        scheduler = StdSchedulerFactory.getDefaultScheduler();

        event();

        scheduler.start();

    }

    private void event(){
        try {

            JobDetail job = JobBuilder.newJob(JobFactory.class).withIdentity("job_getJobId()", "group").build();

            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger_getTriggerId()", "group").startNow().withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?")).build();

            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error("{%s}",e);
        }

    }

    private void addEchoJob(){}
}
