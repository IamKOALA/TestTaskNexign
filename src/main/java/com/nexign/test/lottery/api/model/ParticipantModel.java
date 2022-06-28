package com.nexign.test.lottery.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ParticipantModel {
    private String name;
    private String city;
    private Integer age;
}
