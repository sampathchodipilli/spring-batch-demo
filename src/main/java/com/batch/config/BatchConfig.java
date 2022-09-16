package com.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.listeners.JobResultListener;
import com.batch.listeners.StepResultListener;
import com.batch.tasklet.StepOne;
import com.batch.tasklet.StepThree;
import com.batch.tasklet.StepTwo;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private JobResultListener jobResultListener;
	
	@Autowired
	private StepResultListener stepResultListener;
	
	@Autowired
	private StepOne stepOne;
	
	@Autowired
	private StepTwo stepTwo;
	
	@Autowired
	private StepThree stepThree;
	
	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("first-job")
				.incrementer(new RunIdIncrementer())
				.listener(jobResultListener)
				.start(stepOne())
				.next(stepTwo())
				.next(stepThree())
				.on("COMPLETED").end()
				.build()
				.build();
	}
	
	public Step stepOne() {
		return stepBuilderFactory.get("step-one")
				.listener(stepResultListener)
				.tasklet(stepOne)
				.build();
	}
	
	public Step stepTwo() {
		return stepBuilderFactory.get("step-two")
				.listener(stepResultListener)
				.tasklet(stepTwo)
				.build();
	}
	
	public Step stepThree() {
		return stepBuilderFactory.get("step-three")
				.listener(stepResultListener)
				.tasklet(stepThree)
				.build();
	}
}
