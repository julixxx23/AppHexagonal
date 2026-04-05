package hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private Long idUsuario;
    private String usuarioUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String rolUsuario;
    private Boolean usuarioEstado;
    private LocalDateTime auditoriaFechaCreacion;
}
