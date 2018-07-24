package com.orca.controller;


import com.orca.domain.RegistrationResult;
import com.orca.domain.ReservationData;
import com.orca.domain.ReservationResult;
import com.orca.entities.Boat;
import com.orca.entities.Reservation;
import com.orca.entities.User;
import com.orca.repository.BoatRepository;
import com.orca.repository.ReservationRepository;
import com.orca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ReservationController {


     @Autowired
    private ReservationRepository reservationRepository;

     @Autowired
     private UserRepository userRepository;

    @Autowired
    private BoatRepository boatRepository;

    @RequestMapping(value =  "/reserve" ,  produces = "application/json")
    @PostMapping
    public ReservationResult reserve(@RequestBody ReservationData reservationData) {
    Date reservationDate;
     User user;
     Reservation reservation;
     Boat targetBoat = null ;
     user = userRepository.findByEmail(reservationData.getEmail());

           if(user == null ) {  //no user found with that email adress
               return new ReservationResult("No user exists with that email!");

      }  else {
               //USERID , DATE , TIME
          try {
             reservationDate  = new SimpleDateFormat("yyyy-MM-dd").parse(reservationData.getDate());
          } catch (ParseException e) {
              return new ReservationResult("Reservation failed due to incorrect date format!");

          }

          reservation  = reservationRepository.findByMemberBookedAndReservationDate(user.getUserId() , reservationDate);

          if(reservation == null) {  //can do reservation

              List<Boat> boats = boatRepository.findByBoatTypeAndReservable(reservationData.getBoatType() , true);
              for (Boat boat : boats) {
           Reservation temp = reservationRepository.findByReservationDateAndReservationTimeAndBoatReserved(reservationDate , reservationData.getTime() , boat.getBoatId());

                  if(temp == null ) {

                      targetBoat = boat;

                      break;
                  }
              }

               if(targetBoat == null) {
                  throw new IllegalStateException("Reservation request failed since we cannot find any boat to be reserved!");
               }
              //member booked is user ID
              reservationRepository.save(new Reservation(reservationDate , reservationData.getTime() , targetBoat.getBoatId() , user.getUserId()));
              return new ReservationResult("Reservation request has been submitted!");



          } else {   //cant do reservation

        return new ReservationResult("Reservation request failed since only a reservation can be done per day.");
          }
      }
    }
}
