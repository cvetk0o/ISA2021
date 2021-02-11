package isa20.back.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PharmacyDTO {

    private long id;

    private String name;

    private String description;

    private String country;

    private String city;

    private String street;

    private String number; //building number

    public PharmacyDTO() {
    }

    public PharmacyDTO(long id, String name, String description) {

        this.id = id;
        this.name = name;
        this.description = description;
    }
}
