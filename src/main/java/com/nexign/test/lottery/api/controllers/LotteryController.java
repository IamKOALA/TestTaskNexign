package com.nexign.test.lottery.api.controllers;

import com.nexign.test.lottery.api.model.ParticipantModel;
import com.nexign.test.lottery.api.model.WinnerModel;
import com.nexign.test.lottery.api.services.ParticipantService;
import com.nexign.test.lottery.api.services.WinnerService;
import com.nexign.test.lottery.converters.ParticipantConverter;
import com.nexign.test.lottery.converters.WinnerConverter;
import com.nexign.test.lottery.impl.entities.Participant;
import com.nexign.test.lottery.impl.entities.Winner;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lottery")
public class LotteryController {
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private WinnerService winnerService;
    @Autowired
    private ParticipantConverter participantConverter;

    @Autowired
    private WinnerConverter winnerConverter;

    @PostMapping("/participant")
    public void addParticipant(@RequestBody ParticipantModel participant) {
        participantService.createParticipant(
                participantConverter.convertFromModel(participant)
        );
    }

    @GetMapping("/participant")
    public List<ParticipantModel> getParticipants() {
        return participantConverter.convertToModel(
                participantService.getAllParticipants()
        );
    }

    @GetMapping("/start")
    public WinnerModel startLottery() {
        final Pair<Winner, Participant> winnerAndParticipant = winnerService.finishLottery();

        participantService.deleteAll();

        return winnerConverter.convertToModel(
                winnerAndParticipant.getKey(),
                winnerAndParticipant.getValue()
        );
    }

    @GetMapping("/winner")
    public List<WinnerModel> getWinners() {
        final List<Winner> winners = winnerService.getAllWinners();

        final Map<Long, Participant> participantIdToParticipant =
                participantService.loadByIds(
                                winners
                                        .stream()
                                        .map(Winner::getParticipantId)
                                        .collect(Collectors.toSet())
                        )
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        Participant::getId,
                                        Function.identity())
                        );

        final Map<Long, Participant> winnerIdToParticipant =
                winners.stream().collect(Collectors.toMap(Winner::getId, v -> {
                    Long participantId = v.getParticipantId();
                    return participantIdToParticipant.get(participantId);
                }));

        return winnerConverter.convertToModel(winners, winnerIdToParticipant);
    }


}
