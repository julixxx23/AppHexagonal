package hexagonal.developer.usuario.infrastructure.adapter.out.persistence.mapper;

import hexagonal.developer.usuario.domain.model.RolesUsuario;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPersistenceMapper {

    public Usuario toDomain(UsuarioEntity entity){
        return Usuario.builder()
                .idUsuario(entity.getIdUsuario())
                .usuarioUsuario(entity.getUsuarioUsuario())
                .nombreUsuario(entity.getNombreUsuario())
                .apellidoUsuario(entity.getApellidoUsuario())
                .contrasenaHash(entity.getContrasenaHash())
                .rolUsuario(entity.getRolUsuario().name())
                .usuarioEstado(true)
                .build();
    }

    public UsuarioEntity toEntity(Usuario usuario){
        return UsuarioEntity.builder()
                .idUsuario(usuario.getIdUsuario())
                .usuarioUsuario(usuario.getUsuarioUsuario())
                .nombreUsuario(usuario.getNombreUsuario())
                .apellidoUsuario(usuario.getApellidoUsuario())
                .contrasenaHash(usuario.getContrasenaHash())
                .rolUsuario(RolesUsuario.valueOf(usuario.getRolUsuario()))
                .usuarioEstado(true)
                .build();
    }
}
