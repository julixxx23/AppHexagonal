package hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto;

import hexagonal.developer.usuario.domain.model.RolesUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateRequest {

    @NotBlank(message = "El nombre del usuario es obligatorio")
    @Size(max = 20, message = "El maximo de caracteres es de 20")
    private String usuarioUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150, message = "El maximo de caracteres es de 150")
    private String  nombreUsuario;

    @NotBlank(message  = "El apellido es obligatorio")
    @Size(max = 150, message = "El maximo de caracteres es de 150")
    private String apellidoUsuario;

    @NotBlank(message = "La contrasena es obligatoria")
    @Size(min = 8, max = 10, message = "El maximo de caracteres de la contraseña es de 20")
    private String contrasenaHash;

    @NotBlank(message = "El rol es obligatorio")
    private RolesUsuario rol;

    private Boolean usuarioEstado;


}
