package com.samucatezu.pim2.controllers;

import com.samucatezu.pim2.exception.ResourceNotFoundException;
import com.samucatezu.pim2.models.User;
import com.samucatezu.pim2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String email) {
        List<User> users = new ArrayList<User>();

        if (email == null)
            userRepository.findAll().forEach(users::add);
        else
            userRepository.findByEmail(email).forEach(users::add);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/User/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PutMapping("/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
//        User _user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));
//
//        _user.setTitle(user.getTitle());
//        _user.setDescription(user.getDescription());
//        _user.setPublished(user.isPublished());
//
//        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
//    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
