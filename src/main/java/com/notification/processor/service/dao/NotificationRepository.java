package com.notification.processor.service.dao;

import com.notification.processor.service.entities.NotificationJobEntity;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Named;

/**
 * Created by Ext_IdanF on 12/01/2015.
 */
@Named
public interface NotificationRepository extends CrudRepository<NotificationJobEntity, Long> {

}
