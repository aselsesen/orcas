package com.orca.domain;

public class AvailableHoursQuery {



    private String reservationDate;
    private String boatType;


    public AvailableHoursQuery() {
    }


    public AvailableHoursQuery(String reservationDate, String boatType) {
        this.reservationDate = reservationDate;
        this.boatType = boatType;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }
}
