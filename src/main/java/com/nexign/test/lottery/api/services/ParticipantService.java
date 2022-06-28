package com.nexign.test.lottery.api.services;

import com.nexign.test.lottery.impl.entities.Participant;

import java.util.List;
import java.util.Set;

public interface ParticipantService {
    void createParticipant(Participant participant);

    List<Participant> getAllParticipants();

    Long getParticipantCount();

    Participant loadById(Long id);

    List<Participant> loadByIds(Set<Long> ids);

    Participant getNthParticipant(Long offset);

    List<Participant> deleteAll();
}
