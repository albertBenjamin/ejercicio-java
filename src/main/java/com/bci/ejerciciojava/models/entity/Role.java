package com.bci.ejerciciojava.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Role")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ROLES")
@Data @NoArgsConstructor
public class Role implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idRoleSeq")
    @SequenceGenerator(name = "idRoleSeq" , allocationSize = 1)
    @Column(name="id")
    private Long id;

    @Column(name="authority",unique = true)
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> users;

    public List<User> getUsers() {
        return users==null ? new ArrayList<>():users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
