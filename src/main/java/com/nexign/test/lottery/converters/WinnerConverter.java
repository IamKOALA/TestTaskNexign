package com.nexign.test.lottery.converters;

import com.nexign.test.lottery.api.model.WinnerModel;
import com.nexign.test.lottery.impl.entities.Participant;
import com.nexign.test.lottery.impl.entities.Winner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WinnerConverter {

    @Autowired
    private ParticipantConverter participantConverter;
    public WinnerModel convertToModel(Winner winner, Participant participant) {
        return new WinnerModel(
                participantConverter.convertToModel(participant),
                winner.getPrizeAmount()
        );
    }

    public List<WinnerModel> convertToModel(List<Winner> winners, Map<Long, Participant> winnerIdToParticipant) {
        return winners.stream().map(
                winner -> this.convertToModel(winner, winnerIdToParticipant.get(winner.getId()))
        ).collect(Collectors.toList());
    }
}
