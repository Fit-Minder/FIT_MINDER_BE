package com.be.fitminder.repository;

import com.be.fitminder.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like , Long> {
}
