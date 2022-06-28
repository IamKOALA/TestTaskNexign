package com.nexign.test.lottery.impl.repository;

import com.nexign.test.lottery.impl.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Query(value = "SELECT * FROM participants WHERE is_active = TRUE", nativeQuery = true)
    public List<Participant> getAll();

    @Query(value = "SELECT COUNT(*) FROM participants WHERE is_active = TRUE", nativeQuery = true)
    public Long countActive();

    @Query(value = "SELECT * FROM participants WHERE is_active = TRUE LIMIT 1 OFFSET :offset-1", nativeQuery = true)
    public Optional<Participant> getNthRow(
            @Param("offset") Long offset
    );

    public List<Participant> findByIdIn(Set<Long> participantIdSet);

    @Query(value = "UPDATE participants SET is_active = FALSE RETURNING *", nativeQuery = true)
    public List<Participant> setNonActive();
}
