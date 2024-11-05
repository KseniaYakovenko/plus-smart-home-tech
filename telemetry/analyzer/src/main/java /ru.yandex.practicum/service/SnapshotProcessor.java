package ru.yandex.practicum.service;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;


@Slf4j
@Component
@RequiredArgsConstructor
public class SnapshotProcessor implements Runnable {

    private final AnalyzerProcessor analyzerProcessor;

    @Override
    public void run() {
        log.info("Snapshot processor started");
        // Цикл для регулярного запуска обработки снапшотов
        while (true) {
            try {
                Thread.sleep(5000); // задержка для демонстрации
            } catch (InterruptedException e) {
                log.error("SnapshotProcessor interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    @KafkaListener(topics = "telemetry.snapshots.v1", groupId = "telemetry-snapshots")
    public void onMessage(ConsumerRecord<String, SensorsSnapshotAvro> record) {
        SensorsSnapshotAvro snapshot = record.value();
        log.info("Received snapshot: {}", snapshot);

        analyzerProcessor.processSnapshot(snapshot);
    }
}