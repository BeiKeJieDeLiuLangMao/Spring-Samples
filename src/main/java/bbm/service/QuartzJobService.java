package bbm.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author bbm
 * @date 2020/5/15
 */
@Service
@Slf4j
public class QuartzJobService {

    private static final String JOB_IDENTITY = "TestJob";
    @Autowired
    private Scheduler scheduler;

    public void addJob() throws SchedulerException {
        JobDetail jobDetail = newJob(TestJob.class)
            .usingJobData("data", "testData")
            .withIdentity(JOB_IDENTITY).build();
        Trigger cronTrigger = newTrigger()
            .withIdentity(JOB_IDENTITY)
            .startNow()
            .withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?"))
            .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void updateJobCron() throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(JOB_IDENTITY);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    public void pauseJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(JOB_IDENTITY);
        scheduler.pauseJob(jobKey);
    }

    public void resumeJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(JOB_IDENTITY);
        scheduler.resumeJob(jobKey);
    }

    @Async
    public void runJobNow() throws SchedulerException {
        log.info("Run job now begin, {}", System.currentTimeMillis());
        JobKey jobKey = JobKey.jobKey(JOB_IDENTITY);
        scheduler.triggerJob(jobKey);
        log.info("Run job now end, {}", System.currentTimeMillis());
    }

    public void deleteJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(JOB_IDENTITY);
        scheduler.deleteJob(jobKey);
    }

    public static class TestJob implements Job {

        @Override
        public void execute(JobExecutionContext context) {
            log.info("Job execute: {}", context.getJobDetail());
        }
    }
}
