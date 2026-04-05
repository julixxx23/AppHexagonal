package hexagonal.developer.usuario.domain.port.in;

import hexagonal.developer.usuario.domain.model.Usuario;

public interface GuardarUsuarioPort {
    Usuario guardar(Usuario usuario);
}
