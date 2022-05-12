package com.springjdbctemplate.springjdbctemplatepostgresql.repository;

import com.springjdbctemplate.springjdbctemplatepostgresql.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepo {

    int save(User user);
    int update(User user);
    User findById(int id);
    List<User> findByName(String name);
    int deleteById(Long id);
    List<User> findAll();
    int deleteAll();
}
