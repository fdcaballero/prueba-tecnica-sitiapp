package com.sitiapp.pruebatecnicasitiapp.service;


import com.sitiapp.pruebatecnicasitiapp.dto.UserDetailsImpl;
import com.sitiapp.pruebatecnicasitiapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userService.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Error Usuario no existe");
        }
     
        return UserDetailsImpl.build(user);

    }
}
