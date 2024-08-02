package net.weg.topcare.controller.dto.query;

import net.weg.topcare.entity.PetScheduling;
import net.weg.topcare.entity.Subsidiary;
import net.weg.topcare.enums.ServiceArea;

import java.time.LocalDateTime;
import java.util.List;

public record QueryPostDTO(
        Long clientId,
        String schedulingNumber,
        ServiceArea serviceArea,
        Subsidiary subsidiary,
        LocalDateTime scheduledDate,
        List<PetScheduling> pets
) {
}
