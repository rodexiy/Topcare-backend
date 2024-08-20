package net.weg.topcare.controller.dto.client;

import java.time.LocalDate;

public record ClientPutDTO(String name, String email, String phone, LocalDate birthdate) {
}
