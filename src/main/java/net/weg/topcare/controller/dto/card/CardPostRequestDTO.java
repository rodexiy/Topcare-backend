package net.weg.topcare.controller.dto.card;

import java.time.LocalDate;

public record CardPostRequestDTO(String cardName, String numbers, LocalDate expiration, Long idClient) {
}
