package hexagonal.developer.usuario.domain.port.in;

import hexagonal.developer.usuario.domain.model.Usuario;

public interface EstadoUsuarioPort {
    Usuario estado(Long id, Boolean estado);

}
