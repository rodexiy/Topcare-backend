package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.address.*;
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
        List<AddressGetDTO> listDTO = addresses.stream().map(address -> address.toGetDTO()).toList();
        for(AddressGetDTO a : listDTO){
            if(a.getId() == client.getMainAddress().getId()){
                a.setStandard(true);
            }
        }
        return listDTO;
    }

    @Override
    public Address putAddress(AddressPutDTO addressPutDTO) {
        System.out.println("id ->" + addressPutDTO.id());
        System.out.println("idClient ->" + addressPutDTO.idClient());
        System.out.println("Objeto : " +  addressPutDTO);
        Address address = repository.findById(addressPutDTO.id()).get();
        System.out.println("Address : " + address);
        address.setCep(addressPutDTO.cep());
        address.setCity(addressPutDTO.city());
        address.setComplement(addressPutDTO.complement());
        address.setDistrict(addressPutDTO.district());
        address.setFederativeUnit(addressPutDTO.federativeUnit());
        address.setIdentification(addressPutDTO.identification());
        address.setNumber(addressPutDTO.number());
        address.setStreet(addressPutDTO.street());
        Client client = clientService.findOneClient(addressPutDTO.idClient());
        List<Address> listaDeEnderecosDoCliente = client.getAddress();
        for(Address a : listaDeEnderecosDoCliente){
            if(a.getId().equals(address.getId())){
                listaDeEnderecosDoCliente.remove(a);
                break;
            }
        }
        listaDeEnderecosDoCliente.add(address);
        client.setAddress(listaDeEnderecosDoCliente);
        clientRepository.save(client);
        repository.save(address);
        return address;
    }

    @Override
    public AddressGetDTO postAddress(AddressPostDTO addressPostDTO) {
        System.out.println("id ->" + addressPostDTO.idClient());
        Client client = clientService.findOneClient(addressPostDTO.idClient());
        System.out.println("Client : " + client);
        Address address = new Address(addressPostDTO);
        List<Address> listaDeEnderecosDoCliente = client.getAddress();

        listaDeEnderecosDoCliente.add(address);
        client.setAddress(listaDeEnderecosDoCliente);
        if(client.getMainAddress() == null){
            client.setMainAddress(address);
        }
        address = repository.save(address);
        clientRepository.save(client);
        return address.toGetDTO();
    }

    public AddressGetDTO setMainAddressTrue(Client client, Address address) {
        List<AddressGetDTO> addresses = client.getAddress().stream().map(address1 -> address1.toGetDTO()).toList();
        for (AddressGetDTO a : addresses) {
            if (a.getId().equals(address.getId())) {
                client.setMainAddress(new Address(a));
                repository.save(client.getMainAddress());
                clientRepository.save(client);
                return a;
            }
        }
        return null;
    }

    public AddressGetDTO setMainAddressFalse(Client client, Address address) {
        List<AddressGetDTO> addresses = client.getAddress().stream().map(address1 -> address1.toGetDTO()).toList();
        if (client.getMainAddress().getId().equals(address.getId())) {
            client.setMainAddress(null);
            for (AddressGetDTO a : addresses) {
                if (a.getId().equals(address.getId())) {
                    a.setStandard(false);
                    repository.deleteById(a.getId());
                    clientRepository.save(client);
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public AddressGetDTO patchAddress(Long idClient, Long id) {
        Client client = clientService.findOneClient(idClient);
        Address address = repository.findById(id).get();
        AddressGetDTO result = setMainAddressTrue(client, address);
        if (result != null) {
            return result;
        }
        return setMainAddressFalse(client, address);
    }

    @Override
    public Boolean deleteAddress(Long idClient, Long id) {
        Client client = clientService.findOneClient(idClient);
        List<Address> listaDeEnderecosDoCliente = client.getAddress();
        Address mainAddress = client.getMainAddress();
        if(mainAddress.getId().equals(id)){
            client.setMainAddress(null);
        }
        Address address = repository.findById(id).get();
        for(Address a : listaDeEnderecosDoCliente){
            if(a.getId().equals(address.getId())){
                listaDeEnderecosDoCliente.remove(a);
                break;
            }
        }
        client.setAddress(listaDeEnderecosDoCliente);
        clientRepository.save(client);
        repository.deleteById(id);
        return true;

    }
}
