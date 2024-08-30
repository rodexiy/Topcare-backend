package net.weg.topcare.controller.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.weg.topcare.enums.FederativeUnit;
@Data
@AllArgsConstructor
public class AddressGetDTO {
    Long id;
    String identification;
    String street;
    Integer number;
    String complement;
    String cep;
    String city;
    FederativeUnit federativeUnit;
    String district;
    Boolean standard;
}
