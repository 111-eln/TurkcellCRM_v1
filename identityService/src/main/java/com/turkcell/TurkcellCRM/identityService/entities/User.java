package com.turkcell.TurkcellCRM.identityService.entities;

import com.turkcell.TurkcellCRM.commonPackage.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;


@Table(name="users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name="user_roles",
//            joinColumns = @JoinColumn(name="user_id"),
//            inverseJoinColumns = @JoinColumn(name="role_id")
//    )
//    private Set<Role> authorities;

    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "roleId"))
    private List<Roles> authorities;


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
