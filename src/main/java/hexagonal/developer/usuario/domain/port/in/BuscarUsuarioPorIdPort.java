package hexagonal.developer.usuario.domain.port.in;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.usuario.domain.model.Usuario;

public interface BuscarUsuarioPorIdPort {
    Usuario buscarPorId(Long id);
}
