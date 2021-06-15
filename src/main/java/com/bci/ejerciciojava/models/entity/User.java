package com.bci.ejerciciojava.models.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity(name="User")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USERS")
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id_user", updatable = false, nullable = false )
    private UUID  idUser;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @OneToMany
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

}
