package com.samucatezu.pim2.controllers;

import java.util.ArrayList;
import java.util.List;

import com.samucatezu.pim2.exception.ResourceNotFoundException;
import com.samucatezu.pim2.models.Insurance;
import com.samucatezu.pim2.models.InsurancePlaceHolder;
import com.samucatezu.pim2.models.User;
import com.samucatezu.pim2.repository.InsurancePlaceHolderRepository;
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
    private InsurancePlaceHolderRepository insurancePlaceHolderRepository;

    @GetMapping("/insurances")
    public ResponseEntity<List<InsurancePlaceHolder>> getAllInsurances(@RequestParam(required = false) Long id) throws Exception {
        List<InsurancePlaceHolder> insurances = new ArrayList<InsurancePlaceHolder>();


        if (id == null)
            insurances.addAll(insurancePlaceHolderRepository.findAll());
        else
            insurances.add(insurancePlaceHolderRepository.findById(id).orElseThrow(Exception::new));

        if (insurances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(insurances, HttpStatus.OK);
    }

}
