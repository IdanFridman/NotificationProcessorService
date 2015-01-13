package com.notification.processor.service.service;

import com.notification.processor.service.api.StatusResponse;
import com.notification.processor.service.batch.dto.ProcessFileRequestDTO;
import com.notification.processor.service.dao.NotificationDao;
import com.notification.processor.service.entities.NotificationJobEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by Ext_IdanF on 11/01/2015.
 */
@Named
public class JobServices {

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    private Job processFileJob;

    @Autowired
    @Qualifier("notificationDao")
    private NotificationDao notificationDao;

    public StatusResponse startProcessFileJobExecution(ProcessFileRequestDTO processFileRequestDTO) {
        //TODO: genereate refId
        saveToDB();
        String filePath= processFileRequestDTO.getFilePath();
        String jobId= processFileRequestDTO.getJobId();
        String taskId= processFileRequestDTO.getTaskId();
        String pushMessage= processFileRequestDTO.getPushMessage();
        String refId = "abc111";
        try {
            JobParameters jobParameters = new JobParametersBuilder().addString("refId", refId).addString("jobId", jobId).addString("pushMessage",pushMessage).
                    addString("taskId", taskId).addString("filePath", filePath).addDate("date", new Date()).toJobParameters();
            jobLauncher.run(processFileJob, jobParameters);
            return new StatusResponse(true);
        } catch (JobInstanceAlreadyCompleteException ex) {
            return new StatusResponse(false, "This job has been completed already!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToDB() {

        NotificationJobEntity notificationJobEntity=new NotificationJobEntity();
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
            this.save(notificationJobEntity);
            // notificationRepository.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public NotificationJobEntity save(NotificationJobEntity entity) {
        return notificationDao.save(entity);
    }
}
