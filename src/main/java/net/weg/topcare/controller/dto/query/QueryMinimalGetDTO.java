package net.weg.topcare.controller.dto.query;

import net.weg.topcare.entity.Pet;
import net.weg.topcare.entity.PetScheduling;
import net.weg.topcare.enums.ServiceArea;

import java.time.LocalDateTime;
import java.util.List;

public record QueryMinimalGetDTO(
//        Long id,
//        String nameClient,
        String schedulingNumber,
        List<PetScheduling> pets,
        LocalDateTime scheduledDate,
        ServiceArea serviceArea

){
}
