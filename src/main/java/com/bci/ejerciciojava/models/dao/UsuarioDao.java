package com.bci.ejerciciojava.models.dao;

import com.bci.ejerciciojava.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioDao extends JpaRepository<User, UUID> {

    User findByEmail(String email);
}
