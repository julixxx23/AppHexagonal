package hexagonal.developer.usuario.aplication;

import hexagonal.developer.usuario.domain.exception.UsuarioYaExisteException;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.ActualizarUsuarioPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActualizarUsuarioUseCase implements ActualizarUsuarioPort {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public Usuario actualizar(Long id, Usuario usuario){
        usuarioRepositoryPort.buscarPorId(id);
        if(usuarioRepositoryPort.existePorNombreExcluyendoId(usuario.getNombreUsuario(), id)){
            throw new UsuarioYaExisteException("El nombre de usuario ya existe");
        }
        return usuarioRepositoryPort.actualizar(id, usuario);
    }
}
