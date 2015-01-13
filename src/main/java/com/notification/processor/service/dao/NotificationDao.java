package com.notification.processor.service.dao;

import com.notification.processor.service.entities.NotificationJobEntity;

/**
 * Created by Ext_IdanF on 13/01/2015.
 */
public interface NotificationDao {

    NotificationJobEntity save(NotificationJobEntity entity);
}
