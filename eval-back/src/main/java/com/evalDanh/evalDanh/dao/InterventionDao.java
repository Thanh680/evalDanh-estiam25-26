package com.evalDanh.evalDanh.dao;

import com.evalDanh.evalDanh.models.Projet;
import com.evalDanh.evalDanh.models.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionDao extends JpaRepository<Intervention,Integer> {
    List<Intervention> findBySalaries_Id(Integer id);
}
