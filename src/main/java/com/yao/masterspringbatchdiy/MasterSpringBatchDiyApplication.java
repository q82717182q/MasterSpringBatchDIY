package com.yao.masterspringbatchdiy;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableBatchProcessing
@SpringBootApplication
public class MasterSpringBatchDiyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterSpringBatchDiyApplication.class, args);
    }

    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;
    public Job HelloWorldJob(){
        return jobs.get("HelloWorldJob")
                .start(HelloWorldStep())
                .build();
    }

    public Step HelloWorldStep(){
        return steps.get("HelloWorldStep")
                .tasklet(HelloWorldTasklet())
                .build();
    }

    public Tasklet HelloWorldTasklet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello World by Spring Batch");
                return RepeatStatus.FINISHED;
            }
        };
    }


}
