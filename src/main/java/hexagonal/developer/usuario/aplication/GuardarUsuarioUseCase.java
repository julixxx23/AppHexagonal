package hexagonal.developer.usuario.aplication;

import hexagonal.developer.usuario.domain.exception.UsuarioYaExisteException;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.GuardarUsuarioPort;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
public class GuardarUsuarioUseCase implements GuardarUsuarioPort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario guardar(Usuario usuario) {
        if (usuarioRepositoryPort.existePorUsuarioUsuario(usuario.getUsuarioUsuario())) {
            throw new UsuarioYaExisteException("El nombre de usuario ya está en uso");
        }

        Usuario usuarioConHash = Usuario.builder()
                .idUsuario(usuario.getIdUsuario())
                .usuarioUsuario(usuario.getUsuarioUsuario())
                .nombreUsuario(usuario.getNombreUsuario())
                .apellidoUsuario(usuario.getApellidoUsuario())
                .contrasenaHash(passwordEncoder.encode(usuario.getContrasenaHash()))
                .rolUsuario(usuario.getRolUsuario())
                .activo(true)
                .usuarioEstado(true)
                .build();

        return usuarioRepositoryPort.guardar(usuarioConHash);
    }
}

