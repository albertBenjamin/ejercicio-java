package com.bci.ejerciciojava.models.dao;

import com.bci.ejerciciojava.models.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@CacheConfig(cacheNames = "users")
public interface UsuarioDao extends JpaRepository<User, UUID> {

    User findByEmail(String email);
    @Cacheable
    Optional<User> findByName(String name);
    @Cacheable
    List<User> findAll();
    @Caching(evict = {
            @CacheEvict(key = "#p0.id"),
            @CacheEvict(key = "#p0.name")
    })
    <S extends User> S save(S entity);

    @Cacheable
    Optional<User> findById(UUID id);

}
