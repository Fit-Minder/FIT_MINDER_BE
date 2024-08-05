package com.be.fitminder.domain;

import com.be.fitminder.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Stretching extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stretching_id")
    private Long id;

    private String name;

    private String part;

    private String effect;

    private String reason;

    private String durationTime;

    private String guide;
}
