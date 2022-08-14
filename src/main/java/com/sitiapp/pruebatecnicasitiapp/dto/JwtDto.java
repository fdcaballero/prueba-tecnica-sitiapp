package com.sitiapp.pruebatecnicasitiapp.dto;

import com.sitiapp.pruebatecnicasitiapp.entity.Profile;
import com.sitiapp.pruebatecnicasitiapp.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private User user;

    public JwtDto(String token, UserDetailsImpl user, String perfil) {
        this.token = token;
        this.user = new User(user.getId(), user.getUsername(), user.getName(), user.getLastName(), user.getPassword(), new Profile());
        this.user.getPerfil().setName(perfil);

    }

}
