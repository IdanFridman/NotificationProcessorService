package com.notification.processor.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Ext_IdanF on 25/12/2014.
 */
@ComponentScan({"com.notification.processor.service"})
@EnableAutoConfiguration
@Configuration
@ImportResource({
        "classpath:integration-context.xml","classpath:launch-context.xml"})//,"classpath:applicationContext-NotificationProcessorService.xml"
//})
//@Import({ ServletConfiguration.class, WebappConfiguration.class })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.printf("hello man");
    }
}
