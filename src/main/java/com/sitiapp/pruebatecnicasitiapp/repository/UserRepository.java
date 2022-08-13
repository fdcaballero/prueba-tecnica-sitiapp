package com.sitiapp.pruebatecnicasitiapp.repository;

import com.sitiapp.pruebatecnicasitiapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUserName(String username);
}
