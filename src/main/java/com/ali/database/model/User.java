package com.ali.database.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @NotNull
    public String username,password,email,roles;

    public String profileAvatarPath;

    public LocalDate lastUsage;

    @NotNull
    public long spaceUsed;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "dubsmashCreator")
    public List<Dubsmash> dubsmashes;

    public User(){

    }

    public User(String username,String password,String email,String [] userRoles){
        this.username = username;
        this.password = password;
        this.email = email;
        StringBuilder builder = new StringBuilder();
        builder.append(userRoles[0]);
        for (int i = 1; i < userRoles.length; i++) {
            builder.append(roleDelimiter + roleDelimiter);
        }
        roles = builder.toString();
        lastUsage = LocalDate.now();
        spaceUsed = 0;
    }

    private static final String roleDelimiter = ":";

    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] userRoles = roles.split(roleDelimiter);
        ArrayList grantedAuthorities = new ArrayList();
        for (String userRole : userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole));
        }
        String s = Integer.toString(2);
        return grantedAuthorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        LocalDate expirationDate = lastUsage.plusYears(2);
        return LocalDate.now().isBefore(expirationDate);
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}

