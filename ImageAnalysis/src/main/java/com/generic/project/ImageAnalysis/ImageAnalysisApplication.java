package com.generic.project.ImageAnalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@ComponentScan(basePackages = { "com.paypal.project.controller",
								"com.paypal.project.service.impl" ,
								"com.paypal.project.dal.impl",
								"com.paypal.project.validation"
							  })
public class ImageAnalysisApplication {

	private static Logger logger = LoggerFactory.getLogger(ImageAnalysisApplication.class);

	/**
	 * bean defination for the common executor service with a global cap that is defined by the configuration
	 * 	job.process.concurrency
	 * @param concurrency the number of jobs that will be running at a time.
	 * @return the Executor
	 */
	@Bean
	public ExecutorService getExecutor(@Value("${job.process.concurrency}") int concurrency) {
		if(concurrency > 500) {
			logger.warn("concurrency is {} uncharacteristically high . this should be placed at a reasonable limit considering " +
					"with is allowed by the platform,swap size and ulimit",concurrency);
		}
		return Executors.newFixedThreadPool(concurrency);
	}
	public static void main(String[] args) {
		SpringApplication.run(ImageAnalysisApplication.class, args);
	}
}
