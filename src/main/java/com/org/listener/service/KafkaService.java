package com.org.listener.service;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.org.listener.dto.OrderSearch;
import com.org.listener.dto.ProductSearch;

public class KafkaService {
    private final String topic;
    private final Properties props;
    

    public KafkaService(String brokers, String username, String password) {
        this.topic = username + "-swipe-in-local";
        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, username, password);
        String serializer = StringSerializer.class.getName();
        String deserializer = StringDeserializer.class.getName();
        props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("group.id", username + "-consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "10000");
        props.put("key.deserializer", deserializer);
        props.put("value.deserializer", deserializer);
        props.put("key.serializer", serializer);
        props.put("value.serializer", serializer);
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("sasl.jaas.config", jaasCfg);
    }

    public OrderSearch consume() {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
			consumer.subscribe(Arrays.asList(topic));
			while (true) {
				
			    ConsumerRecords<String, String> records = consumer.poll(1000);
			    for (ConsumerRecord<String, String> record : records) {
			        System.out.printf("%s [%d] offset=%d, key=%s, value=\"%s\"\n",
									  record.topic(), record.partition(),
									  record.offset(), record.key(), record.value());
			        OrderSearch result =new Gson().fromJson(record.value(), OrderSearch.class);
			        return result;
				}
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

}
