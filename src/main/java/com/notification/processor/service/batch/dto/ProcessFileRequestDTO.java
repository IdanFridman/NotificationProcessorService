package com.notification.processor.service.batch.dto;

/**
 * Created by Ext_IdanF on 11/01/2015.
 */
public class ProcessFileRequestDTO {

    String filePath;
    String jobId;
    String taskId;
    String pushMessage;

    public String getPushMessage() {
        return pushMessage;
    }

    public void setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "ProcessFileDTO{" +
                "filePath='" + filePath + '\'' +
                ", jobId='" + jobId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", pushMessage='" + pushMessage + '\'' +
                '}';
    }
}
