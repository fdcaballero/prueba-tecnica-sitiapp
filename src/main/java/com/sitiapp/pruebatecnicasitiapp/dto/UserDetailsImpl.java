package com.sitiapp.pruebatecnicasitiapp.dto;

import com.sitiapp.pruebatecnicasitiapp.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class UserDetailsImpl implements UserDetails {


    private Integer id;

    private String lastName;
    private String name;
    private String password;
    private String userName;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Integer id, String name, String userName, String lastName, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.userName = userName;
        this.id = id;
        this.password = password;
        this.authorities = authorities;
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getPerfil().getName()));

        return new UserDetailsImpl(user.getId(), user.getName(), user.getUserName(), user.getLastName(), user.getPassword(), authorities);
    }


    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
