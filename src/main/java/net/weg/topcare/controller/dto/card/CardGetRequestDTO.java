package net.weg.topcare.controller.dto.card;

import java.time.LocalDate;

public record CardGetRequestDTO(String cardName, String numbers, LocalDate expiration, boolean standard, Long id) {
}
