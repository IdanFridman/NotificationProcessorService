package com.notification.processor.service.dao;

import com.notification.processor.service.entities.NotificationJobEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Ext_IdanF on 13/01/2015.
 */
@Repository("notificationDao")
public class NotificationDaoImpl extends AbstractJpaDAO<NotificationJobEntity, Long> implements NotificationDao {

    private static final Logger logger = LoggerFactory.getLogger(NotificationDaoImpl.class);

    public NotificationDaoImpl() {
        super(NotificationJobEntity.class);
    }

    @Override
    public NotificationJobEntity save(final NotificationJobEntity entity) {
        super.persist(entity);
        logger.debug("Persist a Notification entity in persistence store with Job-ID {}", entity.getJobId());
        return entity;
    }
}