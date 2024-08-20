package net.weg.topcare.controller.dto.address;

import net.weg.topcare.enums.FederativeUnit;

public record AddressGetDTO(Long id, String identification, Boolean standard, String street, Integer number, String complement, String cep, String city, FederativeUnit federativeUnit) {
}
