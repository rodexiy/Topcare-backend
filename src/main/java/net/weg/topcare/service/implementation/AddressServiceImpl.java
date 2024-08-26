package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.address.AddressGetDTO;
import net.weg.topcare.controller.dto.address.AddressPatchDTO;
import net.weg.topcare.controller.dto.address.AddressPostDTO;
import net.weg.topcare.controller.dto.address.AddressPutDTO;
import net.weg.topcare.entity.Address;
import net.weg.topcare.entity.Client;
import net.weg.topcare.repository.AddressRepository;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.service.interfaces.AddressInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressInterface {
    private final AddressRepository repository;
    private final ClientServiceImpl clientService;
    private final ClientRepository clientRepository;
    @Override
    public List<AddressGetDTO> getAllAddresses(Long id) {
        Client client = clientService.findOneClient(id);
        List<Address> addresses = client.getAddress();
        return addresses.stream().map(Address::toGetDTO).toList();
    }

    @Override
    public Address putAddress(AddressPutDTO addressPutDTO) {
        Address address = new Address(addressPutDTO);
        repository.save(address);
        return address;
    }

    @Override
    public Address postAddress(AddressPostDTO addressPostDTO) {
        System.out.println("id ->" + addressPostDTO.idClient());
        Client client = clientService.findOneClient(addressPostDTO.idClient());
        System.out.println("Client : " + client);
        Address address = new Address(addressPostDTO);
        List<Address> listaDeEnderecosDoCliente = client.getAddress();

        address.setStandard(listaDeEnderecosDoCliente.isEmpty());

        listaDeEnderecosDoCliente.add(address);
        client.setAddress(listaDeEnderecosDoCliente);
        address = repository.save(address);
        clientRepository.save(client);
        return address;
    }

    @Override
    public AddressPatchDTO patchAddress(AddressPatchDTO addressPatchDTO, Long id) {
        Address address = repository.findById(id).get();
        if(address.getStandard()){
            address.setStandard(false);
        }else{
            address.setStandard(true);
        }
        repository.save(address);
        return address.toPatchDTO();
    }
}
