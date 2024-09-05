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
    public List<Address> getAllAddresses(Long id) {
        Client client = clientService.findOneClient(id);
        System.out.println("Client : " + client.getAddress());
        return client.getAddress();
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
        return address.toGetDTO(client.getMainAddress().getId());
    }

    @Override
    public Long patchMainAddress(Long idClient, AddressIdDTO dto) {
        Client client = clientService.findOneClient(idClient);
        if(client.getMainAddress().getId() == dto.id()){
            return 0L;
        }
        Address address = new Address();
        address.setId(dto.id());
        client.setMainAddress(address);
        clientRepository.save(client);
        return address.getId();
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

    public Long getMainAddressId(Long idClient) {
        System.out.println("Main address chamou");
        Client client = clientService.findOneClient(idClient);
        Address mainAddress = client.getMainAddress();
        if(mainAddress == null){
            return null;
        }
        return mainAddress.getId();
    }
}
