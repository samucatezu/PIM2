package com.samucatezu.pim2.payload.request;

import com.samucatezu.pim2.models.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInsuranceRequest {

    private Long insurancePlaceHolderId;

    private String dependents;

    private String expirationDate;



}
