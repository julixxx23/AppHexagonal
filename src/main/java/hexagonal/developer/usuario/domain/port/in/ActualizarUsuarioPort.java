package hexagonal.developer.usuario.domain.port.in;

import hexagonal.developer.usuario.domain.model.Usuario;

public interface ActualizarUsuarioPort {
    Usuario actualizar(Long id, Usuario usuario);
}
