package com.orca.controller;


import com.orca.domain.*;
import com.orca.entities.Boat;
import com.orca.entities.OperatingHour;
import com.orca.entities.Reservation;
import com.orca.entities.User;
import com.orca.repository.BoatRepository;
import com.orca.repository.ReservationRepository;
import com.orca.repository.UserRepository;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ReservationController {


     @Autowired
    private ReservationRepository reservationRepository;

     @Autowired
     private UserRepository userRepository;

    @Autowired
    private BoatRepository boatRepository;

    @PersistenceContext
    private EntityManager em;


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


    @RequestMapping(value = "/get-available-hours" , produces =  "application/json" )
    @PostMapping
    public GetAvailableHoursResult getAvailableHours( @RequestBody AvailableHoursQuery availableHoursQuery ) {
        Date reservationDate;
        try {
            reservationDate = new SimpleDateFormat("yyyy-MM-dd").parse(availableHoursQuery.getReservationDate());
        } catch (ParseException e) {
            return new GetAvailableHoursResult("Incorrect date format!");

        }

        StoredProcedureQuery storedProcedureQuery =  em.createNamedStoredProcedureQuery("getAvailableHours");
        storedProcedureQuery.setParameter("reservationDate", reservationDate);
        storedProcedureQuery.setParameter("boatType" , availableHoursQuery.getBoatType());

         SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm");

        List<String> list = ((List<OperatingHour>)storedProcedureQuery.getResultList()).stream().map(hour -> sdf.format(hour.getWorkingHour())).collect(Collectors.toList());

return new GetAvailableHoursResult(list);

    }


    @RequestMapping(value = "/reservationInfo"   , produces = "application/json")
    @PostMapping
    public ReservationList reservationInfo(@RequestBody GetReservationInfo getReservationInfo) {
        User user;
        Reservation reservation;

        user = userRepository.findByEmail(getReservationInfo.getEmail());
        if(user == null) {

           throw new IllegalArgumentException("no user found with that e-mail");



        }
    else {
            Calendar calendar = new GregorianCalendar();

            calendar.set(Calendar.DAY_OF_WEEK , Calendar.MONDAY);
             calendar.add(Calendar.DATE , 6);

              return new ReservationList( reservationRepository.findByMemberBookedAndReservationDateBetween(user.getUserId() , new Date() , calendar.getTime() ));

        }
        }



    @RequestMapping(value = "/cancel"   , produces = "application/json")
    @PostMapping
    public CancellationResult cancelReservation(@RequestBody CancellationInfo cancellationInfo) {
Reservation reservation = null;


     if((reservation = reservationRepository.findOne(cancellationInfo.getReservationId()))!= null)
     {
         reservationRepository.delete(reservation);
         return new CancellationResult("Cancellation done successfully!");

     } else {

         return new CancellationResult("No reservation found with that reservationId!");


     }



    }


}
