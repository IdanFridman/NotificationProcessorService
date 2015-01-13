package com.notification.processor.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Ext_IdanF on 25/12/2014.
 */
@ComponentScan({"com.notification.processor.service"})
@EnableAutoConfiguration
@Configuration
@EntityScan({"com.notification.processor.service.entities"})
@ImportResource({
        "classpath:integration-context.xml", "classpath:launch-context.xml","classpath:spring-context-orm.xml"}) //"classpath:applicationContext-NotificationProcessorService.xml"
//})
//@EnableJpaRepositories(basePackages = {"com.notification.processor.service.dao"})
//@Import({ ServletConfiguration.class, WebappConfiguration.class })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.printf("hello man");
    }
}
