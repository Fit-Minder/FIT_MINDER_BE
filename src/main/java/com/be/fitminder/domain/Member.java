package com.be.fitminder.domain;

import com.be.fitminder.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String name;

    private String role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grass> grasses = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Alarm alarm;

    private String fcmToken;

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Like> likeStretching = new ArrayList<>();

    public void changeName(String name) {
        this.name = name;
    }

    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

//    public Member(Long id, String username, String name, String role) {
//        this.id = id;
//        this.username = username;
//        this.name = name;
//        this.role = role;
//    }


}