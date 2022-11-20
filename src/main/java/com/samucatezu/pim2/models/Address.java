package com.samucatezu.pim2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String addressComplement;
    private String addressStreet;
    private String addressNumber;


    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;
}
