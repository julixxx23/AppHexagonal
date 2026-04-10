package hexagonal.developer.usuario.aplication;

import hexagonal.developer.usuario.domain.exception.UsuarioNotFoundException;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.EstadoUsuarioPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EstadoUsuarioUseCase implements EstadoUsuarioPort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public Usuario estado(Long id, Boolean estado) {
        usuarioRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        return usuarioRepositoryPort.estado(id, estado);
    }
}