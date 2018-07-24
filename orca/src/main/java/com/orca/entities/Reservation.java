package com.orca.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name =  "ORCA_RESERVATION" )
public class Reservation {


    @Id
    @Column(name = "RESERVATION_ID" , length=36, unique=true, nullable=false)
    private String reservationId;



    @Column(name="RESERVATION_DATE", nullable = false   , columnDefinition = "1970-01-01")
    private Date reservationDate;


    @Column(name="RESERVATION_TIME", nullable = false    , columnDefinition = "00:00:00")
    private String reservationTime;

    @Column(name="BOAT_RESERVED" , nullable = false)
    private String boatReserved;

    @Column(name="MEMBER_BOOKED" , nullable = false)
    private String memberBooked;


    public Reservation() {

        setReservationId(UUID.randomUUID().toString());
    }



    public Reservation(Date reservationDate, String reservationTime, String boatReserved, String memberBooked) {
        this();
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.boatReserved = boatReserved;
        this.memberBooked = memberBooked;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getBoatReserved() {
        return boatReserved;
    }

    public void setBoatReserved(String boatReserved) {
        this.boatReserved = boatReserved;
    }

    public String getMemberBooked() {
        return memberBooked;
    }

    public void setMemberBooked(String memberBooked) {
        this.memberBooked = memberBooked;
    }
}


