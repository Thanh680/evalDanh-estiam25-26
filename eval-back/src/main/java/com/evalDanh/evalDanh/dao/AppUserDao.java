package com.evalDanh.evalDanh.dao;

import com.evalDanh.evalDanh.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserDao extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findByEmail(String email);

}
