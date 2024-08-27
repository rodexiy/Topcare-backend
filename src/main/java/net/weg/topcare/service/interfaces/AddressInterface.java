package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.address.AddressGetDTO;
import net.weg.topcare.controller.dto.address.AddressPatchDTO;
import net.weg.topcare.controller.dto.address.AddressPostDTO;
import net.weg.topcare.controller.dto.address.AddressPutDTO;
import net.weg.topcare.entity.Address;

import java.util.List;

public interface AddressInterface {
    List<AddressGetDTO> getAllAddresses(Long id);
    Address putAddress(AddressPutDTO addressPutDTO);
    Address postAddress(AddressPostDTO addressPostDTO);
    AddressPatchDTO patchAddress(AddressPatchDTO addressPatchDTO, Long id);
    Boolean deleteAddress(Long idClient, Long id);

}
