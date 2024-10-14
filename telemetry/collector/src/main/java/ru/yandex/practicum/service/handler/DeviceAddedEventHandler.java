package ru.yandex.practicum.service.handler;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.configuration.KafkaConfig;
import ru.yandex.practicum.handler.BaseHubEventHandler;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import ru.yandex.practicum.model.DeviceAddedEvent;
import ru.yandex.practicum.model.DeviceType;
import ru.yandex.practicum.model.HubEvent;
import ru.yandex.practicum.model.HubEventType;

@Service
public class DeviceAddedEventHandler extends BaseHubEventHandler<DeviceAddedEventAvro> {

    public DeviceAddedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.DEVICE_ADDED;
    }

    @Override
    protected DeviceAddedEventAvro mapToAvro(HubEvent event) {
        DeviceAddedEvent deviceAddedEvent = (DeviceAddedEvent) event;

        return DeviceAddedEventAvro.newBuilder()
                .setId(deviceAddedEvent.getId())
                .setType(mapDeviceTypeToAvro(deviceAddedEvent.getDeviceType()))
                .build();
    }

    private DeviceTypeAvro mapDeviceTypeToAvro(DeviceType deviceType) {
        return DeviceTypeAvro.valueOf(deviceType.name());
    }
}
