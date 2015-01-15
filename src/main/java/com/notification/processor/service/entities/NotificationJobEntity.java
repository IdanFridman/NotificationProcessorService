package com.notification.processor.service.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ext_IdanF on 11/01/2015.
 */
@Entity
@Table(name = "notification_job_execution")
public class NotificationJobEntity implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String jobId;
    private String segmentId;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    private String messageBody;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notificationJobEntity", cascade=CascadeType.ALL)
    private Set<NotificationTaskEntity> notificationTaskEntities = new HashSet<NotificationTaskEntity>(
            0);


    public NotificationJobEntity(String segmentId, Date createdDate, String messageBody, Set<NotificationTaskEntity> notificationTaskEntities) {
        this.segmentId = segmentId;
        this.createdDate = createdDate;
        this.messageBody = messageBody;
        this.notificationTaskEntities = notificationTaskEntities;
    }

    public NotificationJobEntity() {
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Set<NotificationTaskEntity> getNotificationTaskEntities() {
        return notificationTaskEntities;
    }

    public void setNotificationTaskEntities(Set<NotificationTaskEntity> notificationTaskEntities) {
        this.notificationTaskEntities = notificationTaskEntities;
    }

    @Override
    public String toString() {
        return "NotificationJobEntity{" +
                "jobId='" + jobId + '\'' +
                ", segmentId='" + segmentId + '\'' +
                ", createdDate=" + createdDate +
                ", messageBody='" + messageBody + '\'' +
                ", notificationTaskEntities=" + notificationTaskEntities +
                '}';
    }
}
