package com.springjdbctemplate.springjdbctemplatepostgresql.controller;

import com.springjdbctemplate.springjdbctemplatepostgresql.model.User;
import com.springjdbctemplate.springjdbctemplatepostgresql.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
        try {
            List<User> users = new ArrayList<User>();
            if (name == null) {
                userRepo.findAll().forEach(users::add);
            } else
                userRepo.findByName(name).forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userRepo.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userRepo.save(new User(user.getName()));
            return new ResponseEntity<>("User was created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        User _user = userRepo.findById(id);
        if (_user != null) {
            _user.setId(id);
            _user.setName(user.getName());
            userRepo.update(_user);
            return new ResponseEntity<>("User was updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find User with id = " + id, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAllUsers() {
        try {
            int numRows = userRepo.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " User(s) successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete tutorials.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
