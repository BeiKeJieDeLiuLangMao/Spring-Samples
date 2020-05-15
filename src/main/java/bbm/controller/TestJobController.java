package bbm.controller;

import bbm.service.QuartzJobService;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bbm
 * @date 2020/5/15
 */
@RestController
@Slf4j
@RequestMapping("/api/v1.0")
public class TestJobController {

    @Autowired
    private QuartzJobService jobService;

    @PostMapping("/job")
    public void addJob() throws SchedulerException {
        jobService.addJob();
    }

    @PutMapping("/job")
    public void updateJob() throws SchedulerException {
        jobService.updateJobCron();
    }

    @PutMapping("/job/pause")
    public void pauseJob() throws SchedulerException {
        jobService.pauseJob();
    }

    @PutMapping("/job/resume")
    public void resumeJob() throws SchedulerException {
        jobService.resumeJob();
    }

    @PutMapping("/job/run")
    public void runJob(HttpSession httpSession) throws SchedulerException {
        log.info("Run job begin, {}", System.currentTimeMillis());
        log.info("Http session attribute, ttt:" + httpSession.getAttribute("ttt"));
        httpSession.setAttribute("ttt", Math.random());
        jobService.runJobNow();
        log.info("Run job end, {}", System.currentTimeMillis());
    }

    @DeleteMapping("/job")
    public void deleteJob() throws SchedulerException {
        jobService.deleteJob();
    }
}
