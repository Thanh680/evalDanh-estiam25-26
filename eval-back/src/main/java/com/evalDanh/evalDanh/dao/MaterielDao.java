package com.evalDanh.evalDanh.dao;

import com.evalDanh.evalDanh.models.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielDao extends JpaRepository<Materiel,Integer> {
}
