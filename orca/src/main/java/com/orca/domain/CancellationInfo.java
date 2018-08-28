package com.orca.domain;

public class CancellationInfo {

    private String reservationId;

    public CancellationInfo(String reservationId) {
        this.reservationId = reservationId;
    }

    public CancellationInfo() {
    }


    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
}
