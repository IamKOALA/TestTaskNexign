package com.nexign.test.lottery.impl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String city;

    private Integer age;

    private Boolean isActive;

    public Participant(
            final String name,
            final String city,
            final Integer age
    ) {
        this.name = name;
        this.city = city;
        this.age = age;
        this.isActive = true;
    }
}
