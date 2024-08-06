package com.be.fitminder.repository;

import com.be.fitminder.domain.Grass;
import com.be.fitminder.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GrassRepository extends JpaRepository<Grass, Long> {

    List<Grass> findGrassesByMemberAndGrassDateAfter(Member member, LocalDate beforeDate);

    Optional<Grass> findGrassByMemberAndGrassDate(Member member, LocalDate grassDate);
}
