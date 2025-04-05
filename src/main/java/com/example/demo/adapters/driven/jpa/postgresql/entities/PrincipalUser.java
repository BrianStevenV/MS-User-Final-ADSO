package com.example.demo.adapters.driven.jpa.postgresql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Data
@Getter
@Setter
public class PrincipalUser implements UserDetails {
    private String email;
    private String password;
    private long id;
    private Collection<? extends GrantedAuthority> auth;

    public static PrincipalUser build(UserInfoEntity user, List<GrantedAuthority> role, long id){
        return new PrincipalUser(user.getEmail(), user.getPassword(), id, role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            return auth;
    }
}
