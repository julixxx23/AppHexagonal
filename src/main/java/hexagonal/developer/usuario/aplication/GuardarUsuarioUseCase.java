package hexagonal.developer.usuario.aplication;

import hexagonal.developer.usuario.domain.exception.UsuarioYaExisteException;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.GuardarUsuarioPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class GuardarUsuarioUseCase implements GuardarUsuarioPort {
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario guardar(Usuario usuario){
        if(usuarioRepositoryPort.existePorNombre(usuario.getNombreUsuario())){
            throw new UsuarioYaExisteException("Nombre de usuario ya existente");
        }
        Usuario usuarioConHash = Usuario.builder()
                .idUsuario(usuario.getIdUsuario())
                .usuarioUsuario(usuario.getUsuarioUsuario())
                .nombreUsuario(usuario.getNombreUsuario())
                .apellidoUsuario(usuario.getApellidoUsuario())
                .contrasenaHash(passwordEncoder.encode(usuario.getContrasenaHash()))
                .rolUsuario(usuario.getRolUsuario())
                .usuarioEstado(usuario.getUsuarioEstado())
                .build();

        return usuarioRepositoryPort.guardar(usuarioConHash);
    }
}
