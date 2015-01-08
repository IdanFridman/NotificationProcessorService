package com.notification.processor.service.controller;

import com.notification.processor.service.api.StatusResponse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by Ext_IdanF on 30/12/2014.
 */
@RestController
@RequestMapping("/batch")
public class BatchJobController {

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    @Qualifier("job")
    private Job job1;

    @RequestMapping(value = "/job1")
    public @ResponseBody
    StatusResponse job1() {
        try {
            JobParameters jobParameters = new JobParametersBuilder().addString("pathToFile", "snids.csv").addDate("date", new Date()).toJobParameters();
            jobLauncher.run(job1,jobParameters);
            return new StatusResponse(true);

        } catch (JobInstanceAlreadyCompleteException ex) {
            return new StatusResponse(false, "This job has been completed already!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
