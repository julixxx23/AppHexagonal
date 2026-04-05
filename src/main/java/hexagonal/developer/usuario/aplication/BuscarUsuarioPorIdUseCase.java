package hexagonal.developer.usuario.aplication;

import hexagonal.developer.usuario.domain.exception.UsuarioNotFoundException;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.BuscarUsuarioPorIdPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class BuscarUsuarioPorIdUseCase implements BuscarUsuarioPorIdPort {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public Usuario buscarPorId(Long id){
        return usuarioRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }
}
