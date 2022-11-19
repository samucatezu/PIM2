package com.samucatezu.pim2.payload.request;

import com.samucatezu.pim2.models.Insurance;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class SignInsuranceRequest {

    @ManyToOne
    @JoinColumn(name = "insuranceId", insertable = false, updatable = false)
    private Insurance insurance;

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
