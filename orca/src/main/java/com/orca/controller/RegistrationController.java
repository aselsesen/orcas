package com.orca.controller;


import com.orca.domain.RegistrationData;
import com.orca.domain.RegistrationResult;
import com.orca.entities.User;
import com.orca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@RestController
public class RegistrationController {


    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/signup" , produces = "application/json")
    @PostMapping
    public RegistrationResult register(@RequestBody RegistrationData registrationData)      {

        User user;
        user= userRepository.findByEmail(registrationData.getEmail());

        if(user == null) {  //no user found with email .*. good to go
            user= new User();
            user.setEmail(registrationData.getEmail());
            user.setFullName(registrationData.getfName());
            user.setPassword(registrationData.getPassword());
            user.setPhone(registrationData.getPhone());
            Date bDate = null;

            try {
                bDate = new SimpleDateFormat("yyyy-MM-dd").parse(registrationData.getbDate());
            } catch (ParseException e) {
                return new RegistrationResult("Sign up failed due to incorrect birthday format!");

            }
            user.setBirthDate(bDate);
            user.setCanSwim(true);
            user.setTermAgreed(true);

            userRepository.save(user);
            return new RegistrationResult("Sign up succeded!");


        }
        else {

            return new RegistrationResult("User already registered.");


        }



    }







}
