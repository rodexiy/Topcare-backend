package net.weg.topcare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PetGender {
    MALE("Macho"),
    FEMALE("Fêmea");

    private final String nome;

}
