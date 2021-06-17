package com.bci.ejerciciojava.models.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


@Entity(name="User")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USERS")
@Data
public class User implements UserDetails,Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id_user", updatable = false, nullable = false )
    private UUID  idUser;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;

    public String getIdUser() {
        return idUser != null ?idUser.toString():"";
    }

    @Column(name="password")
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private List<Phone> phones;
    @Column(name="create_at")
    @CreatedDate
    private LocalDateTime createAt;
    @Column(name="modify_at")
    @LastModifiedDate
    private LocalDateTime modifyAt;
    @Column(name="last_login")
    @LastModifiedDate
    private LocalDateTime lastLogin;
    @Column(name="is_active" )
    private Boolean isActive = true;
    @Column(name="token")
    private String token;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Role> authorities;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.isActive = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null? new ArrayList<>():authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
