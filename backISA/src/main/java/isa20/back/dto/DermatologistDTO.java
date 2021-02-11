package isa20.back.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DermatologistDTO {

    private long id;

    private String name;

    private String lastname;

    private String email;

    private String password;

    //private Address address;
    private String country;

    private String city;

    private String street;

    private String number; //building number

    private String phoneNumber;

    private double avgRate;
}
