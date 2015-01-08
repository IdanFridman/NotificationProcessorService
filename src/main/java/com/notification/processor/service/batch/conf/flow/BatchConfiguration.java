package com.notification.processor.service.batch.conf.flow;

import java.util.List;

import javax.inject.Inject;

import com.notification.processor.service.batch.dto.PushItemDTO;
import com.notification.processor.service.batch.mappers.PushItemFieldSetMapper;
import com.notification.processor.service.batch.tasks.DownloadFileTasklet;
import com.notification.processor.service.batch.tasks.WriteToKafkaTasklet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by Ext_IdanF on 28/12/2014.
 */
//@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

    @Inject
    private WriteToKafkaTasklet writeToKafkaTasklet;

    @Inject
    private StepBuilderFactory steps;

    @Autowired
    private DownloadFileTasklet downloadFileTasklet;


    @Inject
    private JobBuilderFactory jobs;

    @Inject
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() throws Exception {
       // return this.jobs.get("MyTestJob").start(downloadFileFromETStep()).next(processFileStep()).next(pushToKafkaStep()).build();
        return this.jobs.get("MyTestJob").start(processFileStep()).next(pushToKafkaStep()).build();


    }


    private Step pushToKafkaStep() {
        return this.steps.get("step3").tasklet(writeToKafkaTasklet).build();
    }

    private Step downloadFileFromETStep() {
        return this.steps.get("step1").tasklet(downloadFileTasklet).build();
    }

    private static final String OVERRIDDEN_BY_EXPRESSION = null;


    @Bean
    public Step processFileStep() {
        return stepBuilderFactory.get("step2")
                .<PushItemDTO, PushItemDTO>chunk(1) //important to be one in this case to commit after every line read
                .reader(reader(OVERRIDDEN_BY_EXPRESSION))
                        //  .processor(processor())
                .writer(writer())
                        //    .listener(logProcessListener())
                        //     .faultTolerant()
                        //   .skipLimit(10) //default is set to 0
                        //     .skip(MySQLIntegrityConstraintViolationException.class)
                .build();
    }

    @Bean
    public ItemWriter writer() {
        return new ItemWriter() {

            @Override
            public void write(List items) throws Exception {
                PushItemDTO pushItemDTO = (PushItemDTO) items.get(0);
                logger.debug(pushItemDTO.getSnid());
            }
        };
    }

    @Bean
    @Scope(value = "step", proxyMode = ScopedProxyMode.INTERFACES)
    public ItemStreamReader<PushItemDTO> reader(@Value("#{jobParameters[pathToFile]}") String pathToFile) {
        FlatFileItemReader<PushItemDTO> itemReader = new FlatFileItemReader<PushItemDTO>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(new FileSystemResource(pathToFile));
        return itemReader;
    }

   /* @Bean
    public ItemReader<PushItemDTO> reader() {
        FlatFileItemReader<PushItemDTO> reader = new FlatFileItemReader<PushItemDTO>();
        // reader.setLinesToSkip(1);//first line is title definition
        reader.setResource(new ClassPathResource("snids.csv"));
        reader.setLineMapper(lineMapper());
        return reader;
    }*/

  /*  @Bean
    public ItemProcessor<PushItemDTO, PushItemDTO> processor() {
        return
    }Bean*/


    @Bean
    public LineMapper<PushItemDTO> lineMapper() {
        DefaultLineMapper<PushItemDTO> lineMapper = new DefaultLineMapper<PushItemDTO>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(true);
        lineTokenizer.setNames(new String[]{"environment_id","user_id","sn_type_id","game_type_id","platform_id","user_sn_id","udid","email","user_first_name","user_last_name","ip_country","invoke_source","affiliate","installation_ts","user_level","user_balance_coins","user_experience","last_login_ts","is_paying","count_payments","first_transaction_ts","last_transaction_ts","first_transaction_amount","last_transaction_amount","sum_success_tran_amount","sum_net_amount","tier_id","created_ts","updated_ts","test_group"
        });
        BeanWrapperFieldSetMapper<PushItemDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<PushItemDTO>();
        fieldSetMapper.setTargetType(PushItemDTO.class);


        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new PushItemFieldSetMapper());


        return lineMapper;
    }
}
