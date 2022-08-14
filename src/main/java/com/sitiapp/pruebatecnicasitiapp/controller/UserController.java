package com.sitiapp.pruebatecnicasitiapp.controller;


import com.sitiapp.pruebatecnicasitiapp.dto.ChangePsw;
import com.sitiapp.pruebatecnicasitiapp.dto.JwtDto;
import com.sitiapp.pruebatecnicasitiapp.dto.LoginRequest;
import com.sitiapp.pruebatecnicasitiapp.dto.UserDetailsImpl;
import com.sitiapp.pruebatecnicasitiapp.entity.User;
import com.sitiapp.pruebatecnicasitiapp.security.jwt.JwtProvider;
import com.sitiapp.pruebatecnicasitiapp.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("api/v1/user")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("signin")
    public ResponseEntity<?> create(@RequestBody User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        if (!this.userService.exitsUserbyUsername(user.getUserName())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(user));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String("usuario ya esta registrado"));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        User user = this.userService.findByUserName(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public List<User> getAll() {
        return this.userService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        User user = this.userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("update-psw/{id}")
    public ResponseEntity<?> Update(@PathVariable Integer id, @RequestBody ChangePsw password) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<String>(new String("Usuario no encontrado"), HttpStatus.NOT_FOUND);
        }
        if (this.passwordEncoder.matches(password.getCurrentPassword(), user.getPassword())) {
            user.setPassword(this.passwordEncoder.encode(password.getNewPassword()));
            return ResponseEntity.ok(this.userService.save(user));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("login")
    public ResponseEntity<JwtDto> login(@RequestBody @Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String perfil = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList()).get(0);
        JwtDto jwtDto = new JwtDto(jwt, (UserDetailsImpl) userDetails, perfil);

        return ResponseEntity.ok(jwtDto);

    }

    @DeleteMapping("{id}")
    public boolean deleteUser(@PathVariable Integer id) {
        return this.userService.delete(id);
    }
}
