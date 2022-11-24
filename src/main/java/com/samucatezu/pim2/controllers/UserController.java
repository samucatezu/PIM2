package com.samucatezu.pim2.controllers;

import com.samucatezu.pim2.exception.ResourceNotFoundException;
import com.samucatezu.pim2.models.Address;
import com.samucatezu.pim2.models.Insurance;
import com.samucatezu.pim2.models.InsurancePlaceHolder;
import com.samucatezu.pim2.models.User;
import com.samucatezu.pim2.payload.request.SignInsuranceRequest;
import com.samucatezu.pim2.payload.request.SignupRequest;
import com.samucatezu.pim2.payload.request.UpdateUserRequest;
import com.samucatezu.pim2.repository.AddressRepository;
import com.samucatezu.pim2.repository.InsurancePlaceHolderRepository;
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

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    InsurancePlaceHolderRepository insurancePlaceHolderRepository;

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

    @GetMapping("/User/{clientIdentification}")
    public ResponseEntity<User> getUserByClientIdentification(@PathVariable("clientIdentification") String clientIdentification) {
        User user = userRepository.findByClientIdentification(clientIdentification)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with this clientIdentification = " + clientIdentification));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/User/{clientIdentification}/addPlan")
    public ResponseEntity<User> addPlanToUser(@PathVariable("clientIdentification") String clientIdentification, @RequestBody SignInsuranceRequest signInsuranceRequest) {
        User _user = userRepository.findByClientIdentification(clientIdentification)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with this clientIdentification = " + clientIdentification));
        InsurancePlaceHolder insurancePlaceHolder = insurancePlaceHolderRepository.findById(signInsuranceRequest.getInsurancePlaceHolderId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found insurancePlaceHolder with id = " + signInsuranceRequest.getInsurancePlaceHolderId()));

        Insurance insurance = new Insurance(null, signInsuranceRequest.getDependents(), signInsuranceRequest.getExpirationDate(), insurancePlaceHolder);

        _user.addInsurance(insurance);
        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

//    @PostMapping("/User/{email}/addAddress")
//    public ResponseEntity<User> addAddressToUser(@PathVariable("email") String email, @RequestBody User user) {
//        User _user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found User with email = " + email));
//
//        _user.setInsurances(user.getInsurances());
//        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
//    }

    @PatchMapping("/users/{clientIdentification}")
    public ResponseEntity<User> updateUser(@PathVariable("clientIdentification") String clientIdentification, @RequestBody UpdateUserRequest user) {
        User _user = userRepository.findByClientIdentification(clientIdentification)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with this clientIdentification = " + clientIdentification));

        Address address = new Address(null, user.getAddressStreet(), user.getAddressNumber(), user.getAddressComplement());


        _user.setClientPhone(user.getClientPhone());
        _user.setClientBirthDate(user.getClientBirthDate());
        _user.setClientDegree(user.getClientDegree());
        _user.setClientSalary(user.getClientSalary());
        _user.setClientDependents(user.getClientDependents());
        _user.setClientSex(user.getClientSex());
        _user.addAddress(address);
        _user.setFirst_access(false);
        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
