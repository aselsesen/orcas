package com.orca.domain;

import com.orca.entities.OperatingHour;

import java.util.List;

public class GetAvailableHoursResult {


    private String error;

    private List<String> result;

    public GetAvailableHoursResult() {
    }



    public GetAvailableHoursResult(String error) {

        this.error = error;

    }



    public GetAvailableHoursResult(List<String> result) {
        this.result = result;

    }




    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
