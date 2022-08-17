package com.sitiapp.pruebatecnicasitiapp;

import com.sitiapp.pruebatecnicasitiapp.entity.Profile;
import com.sitiapp.pruebatecnicasitiapp.entity.User;
import com.sitiapp.pruebatecnicasitiapp.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Log4j2
public class PruebaTecnicaSitiappApplication {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(PruebaTecnicaSitiappApplication.class, args);
    }


    @PostConstruct
    public void generateUserRoot() {
        User user = new User();
        user.setPassword("1234");
        user.setUserName("root@correo.com");
        user.setName("root");
        user.setLastName("admin");
        user.setPerfil(new Profile("ADMIN"));

        if (!this.userService.exitsUserbyUsername(user.getUserName())) {
            User userdb = this.userService.save(user);
            log.info(userdb.toString());

        } else {
            log.info("USER ROOT -> { userName: " + user.getUserName() + ", password: " + user.getPassword());
        }

    }
}


