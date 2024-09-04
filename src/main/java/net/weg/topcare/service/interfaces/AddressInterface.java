package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.address.*;
import net.weg.topcare.entity.Address;

import java.util.List;

public interface AddressInterface {
    List<Address> getAllAddresses(Long id);
    Address putAddress(AddressPutDTO addressPutDTO);
    AddressGetDTO postAddress(AddressPostDTO addressPostDTO);

    Long patchMainAddress(Long idClient, AddressIdDTO dto);

    Boolean deleteAddress(Long idClient, Long id);

}
