package com.be.fitminder.domain;

import com.be.fitminder.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "likes")
public class Like extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "stretching_id")
    private Stretching stretching;

    public static Like from(Member member , Stretching stretching) {
        return Like.builder()
                .member(member)
                .stretching(stretching)
                .build();
    }
}
