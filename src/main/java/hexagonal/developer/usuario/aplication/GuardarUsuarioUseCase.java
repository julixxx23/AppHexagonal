package hexagonal.developer.usuario.aplication;

import hexagonal.developer.usuario.domain.exception.UsuarioYaExisteException;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.GuardarUsuarioPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GuardarUsuarioUseCase implements GuardarUsuarioPort {
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public Usuario guardar(Usuario usuario){
        if(usuarioRepositoryPort.existePorNombre(usuario.getNombreUsuario())){
            throw new UsuarioYaExisteException("Nombre de usuario ya existente");
        }
        return usuarioRepositoryPort.guardar(usuario);
    }
}
