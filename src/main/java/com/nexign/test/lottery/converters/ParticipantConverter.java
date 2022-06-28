package com.nexign.test.lottery.converters;

import com.nexign.test.lottery.api.model.ParticipantModel;
import com.nexign.test.lottery.impl.entities.Participant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParticipantConverter {
    public Participant convertFromModel(ParticipantModel model) {
        return new Participant(
                model.getName(),
                model.getCity(),
                model.getAge()
        );
    }

    public List<ParticipantModel> convertToModel(
            List<Participant> participants
    ) {
        return participants
                .stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public ParticipantModel convertToModel(
            Participant participant
    ) {
        return new ParticipantModel(
                participant.getName(),
                participant.getCity(),
                participant.getAge()
        );
    }

}
