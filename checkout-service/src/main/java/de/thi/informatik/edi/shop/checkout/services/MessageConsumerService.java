package de.thi.informatik.edi.shop.checkout.services;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class MessageConsumerService {

    private Logger logger = Logger.getLogger(MessageConsumerService.class.getName());
    private String group;
    private boolean isRunning;

    MessageConsumerService() {
        this.isRunning = true;

    }

    public void consume(String topic, String key, Object value) throws UnknownHostException {
        Properties config = new Properties();
        config.put("client.id", InetAddress.getLocalHost().getHostName());
        config.put("bootstrap.servers", "localhost:9092");
        config.put("group.id", group);
        config.put("key.deserializer", StringDeserializer.class.getName());
        config.put("value.deserializer", StringDeserializer.class.getName());

        try(Consumer<String, String> consumer = new KafkaConsumer<>(config)) {
            consumer.subscribe(List.of(topic));
            while (isRunning) {
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofDays(Long.MAX_VALUE));
                records.forEach(el -> logger.info(el.key() + ": " + el.topic()));
                consumer.commitSync();
            }
        }
    }

    private void terminate() {
        this.isRunning = false;
    }
}
