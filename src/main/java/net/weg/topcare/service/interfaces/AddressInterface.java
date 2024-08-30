package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.address.*;
import net.weg.topcare.entity.Address;

import java.util.List;

public interface AddressInterface {
    List<AddressGetDTO> getAllAddresses(Long id);
    Address putAddress(AddressPutDTO addressPutDTO);
    AddressGetDTO postAddress(AddressPostDTO addressPostDTO);
    Boolean patchAddress(Long idClient, Long id);
    Boolean deleteAddress(Long idClient, Long id);

}
