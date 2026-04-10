package hexagonal.developer.shared.fixture;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.usuario.domain.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class UsuarioFixture {

    public static Usuario unUsuarioValido(){
        return Usuario.builder()
                .idUsuario(1L)
                .usuarioUsuario("juanitaOdon")
                .nombreUsuario("Juana")
                .apellidoUsuario("Baltazar")
                .contrasenaHash("Spiderman")
                .rolUsuario("Super_ADMIN")
                .usuarioEstado(true)
                .activo(true)
                .auditoriasFechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 0))
                .build();
    }

    public static Usuario unUsuarioSinId(){
        return Usuario.builder()
                .usuarioUsuario("juliJava")
                .nombreUsuario("Julian")
                .apellidoUsuario("Orellana")
                .contrasenaHash("Superjava")
                .rolUsuario("Super_ADMIN")
                .usuarioEstado(true)
                .activo(true)
                .auditoriasFechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 0))
                .build();

    }
    public static Usuario unUsuarioInactivo() {
        return Usuario.builder()
                .idUsuario(1L)
                .usuarioUsuario("juanitaOdon")
                .nombreUsuario("Juana")
                .apellidoUsuario("Baltazar")
                .contrasenaHash("Spiderman")
                .rolUsuario("Super_ADMIN")
                .usuarioEstado(false)
                .activo(false)
                .auditoriasFechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 0))
                .build();
    }

    public static List<Usuario> unaListaDeUsuarios() {
        return List.of(
                unUsuarioValido(),
                Usuario.builder()
                        .idUsuario(2L)
                        .usuarioUsuario("juliJava")
                        .nombreUsuario("Julian")
                        .apellidoUsuario("Orellana")
                        .contrasenaHash("Superjava")
                        .rolUsuario("Super_ADMIN")
                        .usuarioEstado(true)
                        .activo(true)
                        .auditoriasFechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 0))
                        .build()


        );
    }
    public static PageDomain<Usuario> unPageDomainUsuarios(){
        return new PageDomain<>(unaListaDeUsuarios(), 0, 1, 2L);
    }


}
