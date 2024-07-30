package com.be.fitminder.repository;

import com.be.fitminder.domain.Stretching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StretchingRepository extends JpaRepository<Stretching,Long>{

    List<Stretching> findByPart(String part);

}
