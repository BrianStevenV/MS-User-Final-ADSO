package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.entities.PrincipalUser;
import com.example.demo.adapters.driven.jpa.postgresql.entities.UserEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.UserInfoEntity;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IUserEntityRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IUserInfoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserInfoEntityRepository userInfoEntityRepository;
    @Autowired
    IUserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoEntity userInfo = userInfoEntityRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        UserEntity user = userEntityRepository.findByUserInfoId(userInfo.getId()).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        List<GrantedAuthority> auth = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return PrincipalUser.build(userInfo, auth, user.getId());
    }
}
