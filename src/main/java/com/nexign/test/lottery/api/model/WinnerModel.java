package com.nexign.test.lottery.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WinnerModel {
    private ParticipantModel winner;
    private Long prizeAmount;
}
