package ru.yandex.practicum.service.handler;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.configuration.KafkaConfig;
import ru.yandex.practicum.handler.BaseSensorEventHandler;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;
import ru.yandex.practicum.model.MotionSensorEvent;
import ru.yandex.practicum.model.SensorEvent;
import ru.yandex.practicum.model.SensorEventType;

@Service
public class MotionSensorEventHandler extends BaseSensorEventHandler<MotionSensorAvro> {

    public MotionSensorEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }

    @Override
    protected MotionSensorAvro mapToAvro(SensorEvent event) {
        MotionSensorEvent motionEvent = (MotionSensorEvent) event;

        return MotionSensorAvro.newBuilder()
                .setLinkQuality(motionEvent.getLinkQuality())
                .setMotion(motionEvent.isMotion())
                .setVoltage(motionEvent.getVoltage())
                .build();
    }
}