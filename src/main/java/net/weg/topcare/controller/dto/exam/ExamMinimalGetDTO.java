package net.weg.topcare.controller.dto.exam;

import net.weg.topcare.enums.ServiceArea;

import java.time.LocalDateTime;
import java.util.List;

public record ExamMinimalGetDTO(
        Long id,
//        String nameClient,
        String schedulingNumber,
        List<String> pets,
        LocalDateTime scheduledDate,
        ServiceArea serviceArea

){
}
