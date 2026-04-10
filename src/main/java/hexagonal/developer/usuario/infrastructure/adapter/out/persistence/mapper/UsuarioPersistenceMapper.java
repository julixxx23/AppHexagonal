package hexagonal.developer.usuario.infrastructure.adapter.out.persistence.mapper;

import hexagonal.developer.usuario.domain.model.RolesUsuario;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPersistenceMapper {

    public Usuario toDomain(UsuarioEntity entity) {
        return Usuario.builder()
                .idUsuario(entity.getIdUsuario())
                .usuarioUsuario(entity.getUsuarioUsuario())
                .nombreUsuario(entity.getNombreUsuario())
                .apellidoUsuario(entity.getApellidoUsuario())
                .contrasenaHash(entity.getContrasenaHash())
                .rolUsuario(entity.getRolUsuario().name())
                .usuarioEstado(entity.getUsuarioEstado())
                .activo(entity.getActivo())
                .auditoriasFechaCreacion(entity.getAuditoriaFechaCreacion())
                .build();
    }

    public UsuarioEntity toEntity(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario(usuario.getIdUsuario());
        entity.setUsuarioUsuario(usuario.getUsuarioUsuario());
        entity.setNombreUsuario(usuario.getNombreUsuario());
        entity.setApellidoUsuario(usuario.getApellidoUsuario());
        entity.setContrasenaHash(usuario.getContrasenaHash());
        entity.setRolUsuario(RolesUsuario.valueOf(usuario.getRolUsuario()));
        entity.setActivo(usuario.getActivo());
        entity.setUsuarioEstado(true);
        return entity;
    }
}