package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    User updete(User user);

    User findById(Integer id);

    boolean delete(Integer id);

    User findByUserName(String username);


}
