package net.weg.topcare.controller.dto.client;

import java.time.LocalDate;

public record ClientPutDTO(String name, String email, String password, LocalDate birthdate) {
}
