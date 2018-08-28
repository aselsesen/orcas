package com.orca.repository;

import com.orca.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,String> {



    Reservation findByMemberBookedAndReservationDate(String memberBooked , Date reservationDate) ;

   Reservation findByReservationDateAndReservationTimeAndBoatReserved(Date reservationDate , String reservationTime , String boatReserved);

   List<Reservation> findByMemberBookedAndReservationDateBetween(String memberBooked , Date initialDate , Date finalDate);






}
