package org.developer.learn.common.pojo.mq;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName KafkaProducerSample
 * @Description:
 * @Author lfq
 * @Date 2020/5/19
 **/
public class KafkaProducerSample {
    public static void main(String[] args) {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("zk.connect", "127.0.0.1:2181");

        String topic = "kafka-test-topic";

        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>(topic, "key1", "value1"));
        producer.send(new ProducerRecord<>(topic, "key2", "value2"));
        producer.send(new ProducerRecord<>(topic, "key2", "value3"));
        producer.close();
    }
}
