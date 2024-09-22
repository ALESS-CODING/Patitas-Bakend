package edu.cibertec.patitas.dto.request;

public record LoginRequestDTO(
        String tipoDocumento,
        String numeroDocumento,
        String password
) {
}
