package hexagonal.developer.usuario.aplication;

import hexagonal.developer.usuario.domain.exception.UsuarioNoEncontradoException;
import hexagonal.developer.usuario.domain.exception.UsuarioYaExisteException;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.ActualizarUsuarioPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActualizarUsuarioUseCase implements ActualizarUsuarioPort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        usuarioRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con id: " + id));

        if (usuarioRepositoryPort.existePorUsuarioUsuarioExcluyendoId(usuario.getUsuarioUsuario(), id)) {
            throw new UsuarioYaExisteException("El nombre de usuario ya está en uso");
        }

        return usuarioRepositoryPort.actualizar(id, usuario);
    }
}