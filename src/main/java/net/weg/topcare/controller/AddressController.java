package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.address.AddressGetDTO;
import net.weg.topcare.controller.dto.address.AddressPatchDTO;
import net.weg.topcare.controller.dto.address.AddressPostDTO;
import net.weg.topcare.controller.dto.address.AddressPutDTO;
import net.weg.topcare.entity.Address;
import net.weg.topcare.service.implementation.AddressServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/address")
@CrossOrigin("*")
public class AddressController {
    private final AddressServiceImpl service;
    @GetMapping("/{id}")
    public ResponseEntity<List<AddressGetDTO>> getAllAddress(@PathVariable Long id){
        return new ResponseEntity<>(service.getAllAddresses(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Address> postAddress(@RequestBody AddressPostDTO dto){
        return new ResponseEntity<>(service.postAddress(dto), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Address> putAddress(@RequestBody AddressPutDTO dto){
        return new ResponseEntity<>(service.putAddress(dto), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AddressPatchDTO> patchAddress(@RequestBody AddressPatchDTO dto, @PathVariable Long id){
        return new ResponseEntity<>(service.patchAddress(dto, id), HttpStatus.OK);
    }
    @DeleteMapping("/{idClient}/{id}")
    public ResponseEntity<Boolean> deleteAddress(@PathVariable Long idClient, @PathVariable Long id){
        return new ResponseEntity<>(service.deleteAddress(idClient, id), HttpStatus.OK);
    }
}
