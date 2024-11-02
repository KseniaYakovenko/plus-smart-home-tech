package ru.yandex.practicum.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

    @Getter
    @Setter
    @ToString
    @ConfigurationProperties("analyzer.kafka")
    @Component
    public class AnalyzerKafkaConfig {

        private Properties hubConsumerProperties;
        private Properties snapshotConsumerProperties;
        private Map<String, String> topics;
        public ProducerConfig producer;
        public ConsumerConfig consumer;

//        @Bean
//        public KafkaProducer<String, SpecificRecordBase> kafkaProducer() {
//            return new KafkaProducer<>(producer.properties);
//        }

//        @Bean
//        public KafkaConsumer<String, SensorsSnapshotAvro> kafkaConsumerSensorSnapshot() {
//            return new KafkaConsumer<>(snapshotConsumerProperties);
//        }
//
//        @Bean
//        public static KafkaConsumer<String, HubEventAvro> kafkaConsumerHubEvent() {
//
//            return new KafkaConsumer<>(hubConsumerProperties);
//        }

//        @Getter
//        public record ConsumerConfig(Properties properties) {
//        }
//
//        @Getter
//        public record ConsumerConfig(Properties properties) {
//        }
    }

