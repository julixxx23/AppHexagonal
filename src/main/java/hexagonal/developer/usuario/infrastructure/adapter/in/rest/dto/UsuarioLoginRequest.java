package hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioLoginRequest {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String usuarioUsuario;

    @NotBlank(message = "La contrasena es obliogatoria")
    private String contrasenaHash;
}
