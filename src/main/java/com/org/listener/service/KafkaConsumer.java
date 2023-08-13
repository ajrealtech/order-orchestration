package com.org.listener.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.listener.dto.OrderSearch;

@Service
public class KafkaConsumer {
	
	Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Autowired
	OrderWorkFlowService workflowService;
	
	public void workflow() {
		//	log.info("Inbound message = " + message.getId());
		while(true) {
			String brokers = "glider.srvs.cloudkafka.com:9094";
			String username = "eidflxfs";
			String password = "PxIX3BTEcbFJbljRiixvpCVAuuB1DkIN";
			KafkaService c = new KafkaService(brokers, username, password);
			OrderSearch message = c.consume();
			workflowService.processWorkflow(message);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	}

}
