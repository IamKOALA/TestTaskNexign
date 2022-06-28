package com.nexign.test.lottery.api.services;

import com.nexign.test.lottery.impl.entities.Participant;
import com.nexign.test.lottery.impl.entities.Winner;
import javafx.util.Pair;

import java.util.List;

public interface WinnerService {
    Pair<Winner, Participant> finishLottery();

    List<Winner> getAllWinners();
}
