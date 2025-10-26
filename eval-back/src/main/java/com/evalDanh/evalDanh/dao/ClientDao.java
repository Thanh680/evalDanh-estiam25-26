package com.evalDanh.evalDanh.dao;

import com.evalDanh.evalDanh.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<Client,Integer> {
}
