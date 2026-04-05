package hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseSimple {
    private String username;
    private String nombre;
    private String apellido;
}
