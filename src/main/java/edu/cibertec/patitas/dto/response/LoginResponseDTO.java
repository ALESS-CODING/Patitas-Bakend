package edu.cibertec.patitas.dto.response;

public record LoginResponseDTO(
        String codigo,
        String menssage,
        String usuario,
        String correo
) {
}
