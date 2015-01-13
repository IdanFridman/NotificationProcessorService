package com.notification.processor.service.batch.tasks;

import com.notification.processor.service.dao.NotificationDao;
import com.notification.processor.service.entities.NotificationJobEntity;
import com.notification.processor.service.service.JobServices;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by Ext_IdanF on 11/01/2015.
 */
@Named
public class LogJobStatusToDBTasklet implements Tasklet {


    @Inject
    JobServices jobServices;


    @Inject
    NotificationDao notificationDao;

    @Override

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        saveToDB();
        return RepeatStatus.FINISHED;
    }

    //@Transactional
    public void saveToDB() {


        jobServices.saveToDB();
    }
}
