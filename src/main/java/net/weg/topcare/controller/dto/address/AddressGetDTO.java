package net.weg.topcare.controller.dto.address;

public record AddressGetDTO(String identification, Boolean standard, String street, Integer number, String complement, String cep, String city, String federativeUnit) {
}
