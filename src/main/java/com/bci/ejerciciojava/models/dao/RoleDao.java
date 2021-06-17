package com.bci.ejerciciojava.models.dao;

import com.bci.ejerciciojava.models.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    <S extends Role> S save(S entity);

    List<Role> findAll();
    List<Role> findByAuthority(String authority);
}
