package com.orca.controller;


import com.orca.entities.BoatType;
import com.orca.repository.BoatTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class BoatController {

     @Autowired
     private BoatTypesRepository boatTypesRepository;



    @RequestMapping( method = RequestMethod.GET , path = "/boat/types")
    public List<BoatType> boat() {

        List<BoatType> boatTypes= new ArrayList<>();

        boatTypesRepository.findAll().forEach(boatTypes::add);

        return boatTypes;

    }


}
