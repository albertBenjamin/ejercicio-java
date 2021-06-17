package com.bci.ejerciciojava.models.dao;

import com.bci.ejerciciojava.models.entity.Phone;
import com.bci.ejerciciojava.models.entity.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneDao extends JpaRepository<Phone,Long> {

    <S extends Phone> S save(S entity);

    List<Phone> findAll();
}
