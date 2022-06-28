package com.nexign.test.lottery.impl.services;

import com.nexign.test.lottery.api.services.ParticipantService;
import com.nexign.test.lottery.impl.entities.Participant;
import com.nexign.test.lottery.impl.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No participant found")
class ParticipantNotFoundException extends RuntimeException {

}

@Service
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public void createParticipant(Participant participant) {
        participantRepository.save(participant);
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.getAll();
    }

    @Override
    public Long getParticipantCount() {
        return participantRepository.countActive();
    }

    @Override
    public Participant loadById(Long id)  {
        return participantRepository.getNthRow(id).orElseThrow(ParticipantNotFoundException::new);
    }

    @Override
    public List<Participant> loadByIds(Set<Long> ids) {
        return participantRepository.findByIdIn(ids);
    }

    @Override
    public Participant getNthParticipant(Long offset) {
        return participantRepository.getNthRow(offset).orElseThrow(ParticipantNotFoundException::new);
    }

    @Override
    @Transactional
    public List<Participant> deleteAll() {
        return participantRepository.setNonActive();
    }

}
