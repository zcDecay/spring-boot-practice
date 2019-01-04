package com.springboot.api.dao;

import com.springboot.api.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GirlDao extends JpaRepository<Girl,Integer> {
}
