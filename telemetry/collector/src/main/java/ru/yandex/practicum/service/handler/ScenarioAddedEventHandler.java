package ru.yandex.practicum.service.handler;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.configuration.KafkaConfig;
import ru.yandex.practicum.handler.BaseHubEventHandler;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioConditionAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceActionAvro;
import ru.yandex.practicum.model.*;

import java.util.stream.Collectors;

@Service
public class ScenarioAddedEventHandler extends BaseHubEventHandler<ScenarioAddedEventAvro> {

    public ScenarioAddedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.SCENARIO_ADDED;
    }

    @Override
    protected ScenarioAddedEventAvro mapToAvro(HubEvent event) {
        ScenarioAddedEvent scenarioEvent = (ScenarioAddedEvent) event;

        return ScenarioAddedEventAvro.newBuilder()
                .setName(scenarioEvent.getName())
                .setConditions(
                        scenarioEvent.getConditions().stream()
                                .map(this::mapConditionToAvro)
                                .collect(Collectors.toList())
                )
                .setActions(
                        scenarioEvent.getActions().stream()
                                .map(this::mapActionToAvro)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private ScenarioConditionAvro mapConditionToAvro(ScenarioCondition condition) {
        return ScenarioConditionAvro.newBuilder()
                .setSensorId(condition.getSensorId())
                .setType(ru.yandex.practicum.kafka.telemetry.event.ConditionTypeAvro.valueOf(condition.getType().name()))
                .setOperation(ru.yandex.practicum.kafka.telemetry.event.ConditionOperationAvro.valueOf(condition.getOperation().name()))
                .setValue(condition.getValue())
                .build();
    }

    private DeviceActionAvro mapActionToAvro(DeviceAction action) {
        return DeviceActionAvro.newBuilder()
                .setSensorId(action.getSensorId())
                .setType(ru.yandex.practicum.kafka.telemetry.event.ActionTypeAvro.valueOf(action.getType().name()))
                .setValue(action.getValue())
                .build();
    }
}