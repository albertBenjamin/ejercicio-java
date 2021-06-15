package com.bci.ejerciciojava.models.dao;

import com.bci.ejerciciojava.models.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneDao extends JpaRepository<Phone,Long> {
}
