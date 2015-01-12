package com.notification.processor.service.repository;

import com.notification.processor.service.main.Application;
import com.notification.processor.service.dao.NotificationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by Ext_IdanF on 12/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class NotificationRepositoryTest {

    @Inject
    NotificationRepository notificationRepository;

    @Test
    public void test()
    {
        System.out.printf("test");

    }
}
