package com.nexign.test.lottery.impl.services;

import com.nexign.test.lottery.api.services.ParticipantService;
import com.nexign.test.lottery.api.services.RandomService;
import com.nexign.test.lottery.api.services.WinnerService;
import com.nexign.test.lottery.impl.entities.Participant;
import com.nexign.test.lottery.impl.entities.Winner;
import com.nexign.test.lottery.impl.repository.WinnerRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;
import java.util.List;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Not enough participants yet")
class NotEnoughParticipantException extends RuntimeException {

}

@Service
public class WinnerServiceImpl implements WinnerService {

    @Autowired
    private RandomService randomService;

    @Autowired
    private WinnerRepository winnerRepository;


    @Autowired
    private ParticipantService participantService;

    @Override
    @Transactional
    public Pair<Winner, Participant> finishLottery() {
        final Long participantCount = participantService.getParticipantCount();

        if (participantCount < 2) {
            throw new NotEnoughParticipantException();
        }

        final Long prizeAmount = randomService.getRandomNumber(1L, 1000L);

        final Long winnerOffset = randomService.getRandomNumber(
                1L,
                participantCount
        );

        return saveWinner(winnerOffset, prizeAmount);
    }

    @Override
    public List<Winner> getAllWinners() {
        return winnerRepository.getAll();
    }

    private Pair<Winner, Participant> saveWinner(Long winnerOffset, Long prizeAmount) {
        final Participant participant = participantService.getNthParticipant(winnerOffset);
        return new Pair<>(
                winnerRepository.save(new Winner(participant.getId(), prizeAmount)),
                participant
        );
    }
}
