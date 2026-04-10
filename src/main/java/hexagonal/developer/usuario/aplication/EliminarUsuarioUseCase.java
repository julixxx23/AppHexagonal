package hexagonal.developer.usuario.aplication;

import hexagonal.developer.usuario.domain.exception.UsuarioNotFoundException;
import hexagonal.developer.usuario.domain.port.in.EliminarIUsuarioPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EliminarUsuarioUseCase implements EliminarIUsuarioPort {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public void eliminar(Long id) {
        usuarioRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        usuarioRepositoryPort.eliminar(id);
    }
}
