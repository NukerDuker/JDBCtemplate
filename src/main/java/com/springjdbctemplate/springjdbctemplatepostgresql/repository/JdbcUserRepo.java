package com.springjdbctemplate.springjdbctemplatepostgresql.repository;

import com.springjdbctemplate.springjdbctemplatepostgresql.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserRepo implements UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO user_tab (name) values (?)", new Object[]{user.getName()});
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("UPDATE user_tab SET name=? where id=?", new Object[]{user.getName(), user.getId()});
    }

    @Override
    public User findById(int id) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * from user_tab where id=?", BeanPropertyRowMapper.newInstance(User.class), id);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findByName(String name) {
        String q = "SELECT * from user_tab where name =" + name + " ;";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM user_tab where id=?", id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from user_tab", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from tutorials");
    }
}
