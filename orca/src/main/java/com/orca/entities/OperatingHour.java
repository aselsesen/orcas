package com.orca.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORCA_OPERATING_HOUR")
@NamedStoredProcedureQueries(
        {@NamedStoredProcedureQuery(name = "getAvailableHours", procedureName = "GET_AVAILABLE_HOURS",resultClasses = { OperatingHour.class },
                parameters = { @StoredProcedureParameter( name = "reservationDate",type = Date.class,mode = ParameterMode.IN) ,
                        @StoredProcedureParameter(name = "boatType", type = String.class , mode = ParameterMode.IN)  }   )
        })
public class OperatingHour {


    @Id
    @Column( name = "WORKING_HOUR")
    private Date workingHour;


    public Date getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(Date workingHour) {
        this.workingHour = workingHour;
    }
}
