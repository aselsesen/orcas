package com.orca.controller;

import com.orca.domain.LoginResult;
import com.orca.entities.User;
import com.orca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {



    @Autowired
    private UserRepository userRepository;

     @RequestMapping(method= RequestMethod.GET, path="/login")
    public LoginResult logIn(@RequestParam(value="email")String email, @RequestParam(value="password")String password){

        User user;
        user = userRepository.findByEmail(email);


        if(user == null) {

            return new LoginResult("No user found with that e-mail address.");



        }
        else {   //if user is found

            return new LoginResult(user.getPassword().equals(password) ? "Succesfully logged in!" : "Login failed due to wrong password!");

        }


    }





}
