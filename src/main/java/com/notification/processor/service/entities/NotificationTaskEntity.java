package com.notification.processor.service.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Ext_IdanF on 11/01/2015.
 */
@Entity
@Table(name = "notification_task_execution")
public class NotificationTaskEntity implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long taskId;

    private int jobId;

    private String refId;

    private String status;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @ManyToOne(optional = false)
    private NotificationJobEntity notificationJobEntity;


    public NotificationTaskEntity(int jobId, String refId, String status, Date createdDate, NotificationJobEntity notificationJobEntity) {
        this.jobId = jobId;
        this.refId = refId;
        this.status = status;
        this.createdDate = createdDate;
        this.notificationJobEntity = notificationJobEntity;
    }

    public NotificationTaskEntity() {
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public NotificationJobEntity getNotificationJobEntity() {
        return notificationJobEntity;
    }

    public void setNotificationJobEntity(NotificationJobEntity notificationJobEntity) {
        this.notificationJobEntity = notificationJobEntity;
    }

    @Override
    public String toString() {
        return "NotificationTaskEntity{" +
                "taskId=" + taskId +
                ", jobId=" + jobId +
                ", refId='" + refId + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", notificationJobEntity=" + notificationJobEntity +
                '}';
    }
}
