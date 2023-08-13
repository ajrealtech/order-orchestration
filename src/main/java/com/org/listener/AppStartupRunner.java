package com.org.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.org.listener.service.KafkaConsumer;

@Component
public class AppStartupRunner implements ApplicationRunner {
     	    public static int counter;

     	    @Autowired
     	   KafkaConsumer kafkaConsumer;
     	    
    	    @Override
    	    public void run(ApplicationArguments args) throws Exception {
    	        kafkaConsumer.workflow();
    	    }
    	}