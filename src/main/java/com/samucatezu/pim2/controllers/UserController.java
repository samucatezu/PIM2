package com.samucatezu.pim2.controllers;

import com.samucatezu.pim2.exception.ResourceNotFoundException;
import com.samucatezu.pim2.models.Insurance;
import com.samucatezu.pim2.models.User;
import com.samucatezu.pim2.payload.request.SignInsuranceRequest;
import com.samucatezu.pim2.payload.request.SignupRequest;
import com.samucatezu.pim2.repository.InsuranceRepository;
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

    @Autowired
    InsuranceRepository insuranceRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String email) {
        List<User> users = new ArrayList<User>();

        if (email == null)
            users.addAll(userRepository.findAll());
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/User/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with email = " + email));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/User/{email}/addPlan")
    public ResponseEntity<User> addPlanToUser(@PathVariable("email") String email, @RequestBody User user) {
        User _user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with email = " + email));

        _user.setInsurances(user.getInsurances());
        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

    @PatchMapping("/users/{email}")
    public ResponseEntity<User> updateUser(@PathVariable("email") String email, @RequestBody User user) {
        User _user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with email = " + email));

        _user.setClientPhone(user.getClientPhone());
        _user.setClientBirthDate(user.getClientBirthDate());
        _user.setClientDegree(user.getClientDegree());
        _user.setClientSalary(user.getClientSalary());
        _user.setClientDependents(user.getClientDependents());
        _user.setClientSex(user.getClientSex());
        _user.setAddress(user.getAddress());
        _user.setFirst_acess(user.isFirst_acess());

        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
