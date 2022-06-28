package com.nexign.test.lottery.impl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "winners")
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long participantId;

    private Long prizeAmount;

    public Winner(
            Long participantId,
            Long prizeAmount
    ) {
        this.participantId = participantId;
        this.prizeAmount = prizeAmount;
    }
}
