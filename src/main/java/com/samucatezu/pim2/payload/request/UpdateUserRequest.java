package com.samucatezu.pim2.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequest {

    private String clientSalary;

    private String clientSex;

    private String clientPhone;

    private String clientDependents;

    private String clientBirthDate;

    private String clientDegree;

    private String addressComplement;
    private String addressStreet;
    private String addressNumber;
}
