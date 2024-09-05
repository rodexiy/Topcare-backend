package net.weg.topcare.controller.dto.address;

import net.weg.topcare.enums.FederativeUnit;

public record AddressPutDTO(Long id, Long idClient, String cep, Integer number, String street, String district, String city, FederativeUnit federativeUnit, String complement, String identification) {
}
