package com.evalDanh.evalDanh.dao;

import com.evalDanh.evalDanh.models.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarieDao extends JpaRepository<Salarie,Integer> {
}
