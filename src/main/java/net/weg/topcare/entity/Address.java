package net.weg.topcare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.topcare.controller.dto.address.*;
import net.weg.topcare.enums.FederativeUnit;
import org.springframework.beans.BeanUtils;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 4)
    private Integer number;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 100)
    private String district;

    @Column(nullable = false, length = 100)
    private String city;

    @Enumerated(EnumType.STRING)
    private FederativeUnit federativeUnit;

    @Column(length = 100)
    private String complement;
    @Column(length = 50)
    private String identification;

    public AddressGetDTO toGetDTO(Long idMainAddress) {
        return new AddressGetDTO(this.id, idMainAddress, this.identification,
                this.street, this.number, this.complement, this.cep,
                this.city, this.federativeUnit, this.district);
    }

    public Address(AddressGetDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
    public Address(AddressPutDTO dto){
        this.setCep(dto.cep());
        this.setCity(dto.city());
        this.setComplement(dto.complement());
        this.setDistrict(dto.district());
        this.setFederativeUnit(dto.federativeUnit());
        this.setIdentification(dto.identification());
        this.setNumber(dto.number());
        this.setStreet(dto.street());
        this.setId(dto.id());
    }
    public Address(AddressPostDTO dto){
        this.setCep(dto.cep());
        this.setCity(dto.city());
        this.setComplement(dto.complement());
        this.setDistrict(dto.district());
        this.setFederativeUnit(FederativeUnit.valueOf(dto.federativeUnit()));
        this.setIdentification(dto.identification());
        this.setNumber(dto.number());
        this.setStreet(dto.street());
    }

}
