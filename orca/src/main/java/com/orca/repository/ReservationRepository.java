package com.orca.repository;

import com.orca.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface ReservationRepository extends CrudRepository<Reservation,String> {



Reservation findByMemberBookedAndReservationDate(String memberBooked , Date reservationDate) ;

    Reservation findByAndReservationDate(String memberBooked , Date reservationDate) ;
   Reservation findByReservationDateAndReservationTimeAndBoatReserved(Date reservationDate , String reservationTime , String boatReserved);





}
