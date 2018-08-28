package com.orca.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORCA_BOAT_TYPE")
public class BoatType {

     @Id
     @Column( name = "BOAT_TYPE_NAME")
    private String boatTypeName;



    public String getBoatTypeName() {
        return boatTypeName;
    }

    public void setBoatTypeName(String boatTypeName) {
        this.boatTypeName = boatTypeName;
    }
}
