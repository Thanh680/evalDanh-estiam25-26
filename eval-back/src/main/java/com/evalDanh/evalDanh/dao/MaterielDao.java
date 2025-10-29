package com.evalDanh.evalDanh.dao;

import com.evalDanh.evalDanh.models.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterielDao extends JpaRepository<Materiel,Integer> {
    @Query("SELECT m FROM Materiel m WHERE m.interventions IS EMPTY")
    List<Materiel> findAllWithoutIntervention();
}
