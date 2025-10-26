package com.evalDanh.evalDanh.dao;

import com.evalDanh.evalDanh.models.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetDao extends JpaRepository<Projet,Integer> {
}
