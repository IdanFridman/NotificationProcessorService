package com.notification.processor.service.batch.tasks;

import com.notification.processor.service.batch.dto.KafkaMsgDTO;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.inject.Named;

/**
 * Created by Ext_IdanF on 31/12/2014.
 */
@Named
public class WriteToKafkaTasklet implements Tasklet {

    //@Autowired
   // KafkaProducer kafkaProducer;

    //@Inject
   // MessageChannel inputToKafka;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        sendMessageToKafka("testmessage");
        return RepeatStatus.FINISHED;
    }

    public void sendMessageToKafka(String message) {
    //    KafkaMsgDTO kafkaMsgDTO =new KafkaMsgDTO();
     //   kafkaProducer.send("push_notification", kafkaMsgDTO);
    }
}
