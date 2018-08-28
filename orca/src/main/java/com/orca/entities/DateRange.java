package com.orca.entities;


import javax.persistence.Entity;
import java.util.Date;

public class DateRange {

    private Date initialDate;
    private Date finalDate;


    public DateRange() {
    }

    public DateRange(Date initialDate, Date finalDate) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
