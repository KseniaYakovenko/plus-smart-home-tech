package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ScenarioRemovedEvent extends HubEvent {
    @NotBlank
    @Size(min = 3)
    private String name;

//    maxLength: 2147483647
//    minLength: 3

    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_REMOVED;
    }
}