package com.sitiapp.pruebatecnicasitiapp.repository;

import com.sitiapp.pruebatecnicasitiapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsUserByUserName(String username);
    User findUserByUserName(String username);
}
