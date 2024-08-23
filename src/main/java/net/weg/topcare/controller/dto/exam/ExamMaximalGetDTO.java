package net.weg.topcare.controller.dto.exam;

import net.weg.topcare.entity.PetScheduling;
import net.weg.topcare.entity.Subsidiary;
import net.weg.topcare.enums.ServiceArea;

import java.time.LocalDateTime;
import java.util.List;

public record ExamMaximalGetDTO(
        Long id,
        String nameClient,
        String schedulingNumber,
        ServiceArea serviceArea,
        Subsidiary subsidiary,
        LocalDateTime scheduledDate,
        List<PetScheduling> pets
) {
}
