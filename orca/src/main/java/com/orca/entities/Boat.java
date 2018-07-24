package com.orca.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORCA_BOAT")
public class Boat {


     @Id
     @Column(name = "BOAT_ID"   , nullable =  false)
     private String boatId;



    @Column(name = "BOAT_NAME" )
    private String boatName;


    @Column(name = "BOAT_TYPE"   , nullable =  false)
    private String boatType;


  @Column(name = "RESERVABLE"   , columnDefinition = "true")
    private boolean reservable;


    public String getBoatId() {
        return boatId;
    }

    public void setBoatId(String boatId) {
        this.boatId = boatId;
    }

    public String getBoatName() {
        return boatName;
    }

    public void setBoatName(String boatName) {
        this.boatName = boatName;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public boolean isReservable() {
        return reservable;
    }

    public void setReservable(boolean reservable) {
        this.reservable = reservable;
    }
}
