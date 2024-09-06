package net.weg.topcare.controller.dto.address;

public record AddressPostDTO(Long idClient, String cep, Integer number, String street, String district, String city, String federativeUnit, String complement, String identification) {
}
