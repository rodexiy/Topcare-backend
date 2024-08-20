package net.weg.topcare.controller.dto.client;

public record ClientPatchDTO(String oldPassword, String newPassword) {
}
