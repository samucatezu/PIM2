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
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class InsuranceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @GetMapping("/insurances/{id}")
    public ResponseEntity<Insurance> getInsurancesByUserId(@PathVariable(value = "id") Long Insuranceid) {
        Insurance insurance = insuranceRepository.findById(Insuranceid)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Insurance with id = " + Insuranceid));

        return new ResponseEntity<>(insurance, HttpStatus.OK);
    }

//    @PostMapping("/users/{userId}/insurances")
//    public ResponseEntity<Insurance> createInsurance(@PathVariable(value = "userId") Long userId,
//                                                 @RequestBody Insurance insuranceRequest) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));
//                insuranceRequest.setUser(user);
//                Insurance insurance = insuranceRepository.save(insuranceRequest);
//        return new ResponseEntity<>(insurance, HttpStatus.CREATED);
//    }

// Criar os planos via SQL

    @GetMapping("/insurances")
    public ResponseEntity<List<Insurance>> getAllInsurances(@RequestParam(required = false) Long insuranceId) {
        List<Insurance> insurances = new ArrayList<Insurance>();


        if (insuranceId == null)
            insurances.addAll(insuranceRepository.findAll());
        else
            insurances.addAll(insuranceRepository.findByInsuranceId(insuranceId));

        if (insurances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(insurances, HttpStatus.OK);
    }

}
