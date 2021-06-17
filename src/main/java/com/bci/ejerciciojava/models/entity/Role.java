package com.bci.ejerciciojava.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name="Role")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ROLES")
@Data @AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idRoleSeq")
    @SequenceGenerator(name = "idRoleSeq" , allocationSize = 1)
    @Column(name="id")
    private Long id;

    @Column(name="authority",unique = true)
    private String authority;

}
