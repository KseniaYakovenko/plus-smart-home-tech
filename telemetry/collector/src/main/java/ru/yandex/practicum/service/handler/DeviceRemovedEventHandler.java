package ru.yandex.practicum.service.handler;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.configuration.KafkaConfig;
import ru.yandex.practicum.handler.BaseHubEventHandler;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.model.DeviceRemovedEvent;
import ru.yandex.practicum.model.HubEvent;
import ru.yandex.practicum.model.HubEventType;

@Service
public class DeviceRemovedEventHandler extends BaseHubEventHandler<DeviceRemovedEventAvro> {

    public DeviceRemovedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.DEVICE_REMOVED;
    }

    @Override
    protected DeviceRemovedEventAvro mapToAvro(HubEvent event) {
        DeviceRemovedEvent deviceRemovedEvent = (DeviceRemovedEvent) event;

        return DeviceRemovedEventAvro.newBuilder()
                .setId(deviceRemovedEvent.getId())
                .build();
    }
}