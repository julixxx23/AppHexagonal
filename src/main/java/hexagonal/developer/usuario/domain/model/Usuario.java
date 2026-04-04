package hexagonal.developer.usuario.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Long idUsuario;
    private String usuarioUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String contrasenaHash;
    private String rolUsuario;
    private Boolean usuarioEstado;
    private LocalDateTime fechaCreacion;
}
