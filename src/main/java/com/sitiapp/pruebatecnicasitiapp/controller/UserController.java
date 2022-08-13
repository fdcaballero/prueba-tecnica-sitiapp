package com.sitiapp.pruebatecnicasitiapp.controller;

import com.sitiapp.pruebatecnicasitiapp.dto.ChangePsw;
import com.sitiapp.pruebatecnicasitiapp.entity.User;
import com.sitiapp.pruebatecnicasitiapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(user));
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

/*    @PutMapping("update-psw/{id}")
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
    }*/
}
