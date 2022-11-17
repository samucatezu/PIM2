package com.samucatezu.pim2.controllers;

import java.util.ArrayList;
import java.util.List;

import com.samucatezu.pim2.exception.ResourceNotFoundException;
import com.samucatezu.pim2.models.Insurance;
import com.samucatezu.pim2.models.User;
import com.samucatezu.pim2.repository.InsuranceRepository;
import com.samucatezu.pim2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
public class InsuranceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @GetMapping("/users/{userId}/insurances")
    public ResponseEntity<List<Insurance>> getAllInsurancesByUserId(@PathVariable(value = "userId") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + userId));

        List<Insurance> insurances = new ArrayList<Insurance>();
        insurances.addAll(user.getInsurances());

        return new ResponseEntity<>(insurances, HttpStatus.OK);
    }

    @GetMapping("/insurances/{id}")
    public ResponseEntity<Insurance> getInsurancesByUserId(@PathVariable(value = "id") Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Insurance with id = " + id));

        return new ResponseEntity<>(insurance, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/insurances")
    public ResponseEntity<Insurance> createInsurance(@PathVariable(value = "userId") Long userId,
                                                 @RequestBody Insurance insuranceRequest) {
        Insurance insurance = userRepository.findById(userId).map(user -> {
            user.getInsurances().add(insuranceRequest);
            return insuranceRepository.save(insuranceRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        return new ResponseEntity<>(insurance, HttpStatus.CREATED);
    }

    @PutMapping("/insurances/{id}")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable("id") long id, @RequestBody Insurance insuranceRequest) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InsuranceId " + id + "not found"));

        insurance.setValor(insuranceRequest.getValor());

        return new ResponseEntity<>(insuranceRepository.save(insurance), HttpStatus.OK);
    }

    @DeleteMapping("/insurances/{id}")
    public ResponseEntity<HttpStatus> deleteInsurance(@PathVariable("id") long id) {
        insuranceRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{userId}/insurances")
    public ResponseEntity<List<Insurance>> deleteAllInsurancesOfUser(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }

        insuranceRepository.deleteByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
