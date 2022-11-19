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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String email) {
        List<User> users = new ArrayList<User>();

        if (email == null)
            users.addAll(userRepository.findAll());
        else
            users.addAll(userRepository.findByEmail(email));

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

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User _user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

        _user.setEmail(user.getEmail());
        _user.setUsername(user.getUsername());
        _user.setDocumento(user.getDocumento());
        _user.setTelefone(user.getTelefone());
        _user.setFirst_acess(user.isFirst_acess());
        _user.setFormacao(user.getFormacao());
        _user.setSexo(user.getSexo());
        _user.setData_nascimento(user.getData_nascimento());
        _user.setAddress(user.getAddress());

        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

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
