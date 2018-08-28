package com.orca.domain;

import com.orca.entities.Reservation;

import java.util.List;

public class ReservationList {

    private List<Reservation>  reservationList  ;

    public ReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }



    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
