package isa20.back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PharmacyDTO {

    private long id;

    private String name;

    private String description;

    public PharmacyDTO() {
    }

    public PharmacyDTO(long id, String name, String description) {

        this.id = id;
        this.name = name;
        this.description = description;
    }
}
