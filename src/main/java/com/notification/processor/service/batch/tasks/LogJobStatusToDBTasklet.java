package com.notification.processor.service.batch.tasks;

import com.notification.processor.service.dao.NotificationDao;
import com.notification.processor.service.entities.NotificationJobEntity;
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
    NotificationDao notificationDao;

    @Override

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        saveToDB();
        return RepeatStatus.FINISHED;
    }

    //@Transactional
    public void saveToDB() {


        NotificationJobEntity notificationJobEntity = new NotificationJobEntity();
        notificationJobEntity.setCreatedDate(new Date());
        notificationJobEntity.setMessageBody("hello youu");
        notificationJobEntity.setSegmentId("3aa");
        //  NotificationTaskEntity notificationTaskEntity=new NotificationTaskEntity();
        //  notificationTaskEntity.setCreatedDate(new Date());
        //  notificationTaskEntity.setRefId("23");
        //  notificationTaskEntity.setStatus("success");
        //  notificationTaskEntity.setNotificationJobEntity(notificationJobEntity);
        //  notificationJobEntity.getNotificationTaskEntities().add(notificationTaskEntity);
        try {

         //   notificationRepository.save(notificationJobEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
