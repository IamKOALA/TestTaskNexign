package com.nexign.test.lottery.impl.repository;

import com.nexign.test.lottery.impl.entities.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
    @Query(value = "SELECT * FROM winners", nativeQuery = true)
    public List<Winner> getAll();
}
