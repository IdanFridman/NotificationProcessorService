package com.notification.processor.service.batch.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

/**
 * Created by Ext_IdanF on 05/01/2015.
 */
@Named
public class DownloadFileTasklet implements Tasklet {


    String url = "http://bi-downloads.corp/tasks/000e0f8a3464b2e8ac5c5e3e82b9d82b/extractor.csv";


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Date date = (Date) chunkContext.getStepContext().getJobParameters().get("date");
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(new File("c:/newfile.txt"));
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        return RepeatStatus.FINISHED;
    }
}
