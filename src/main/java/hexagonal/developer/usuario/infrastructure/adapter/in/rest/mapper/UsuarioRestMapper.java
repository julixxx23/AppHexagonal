package hexagonal.developer.usuario.infrastructure.adapter.in.rest.mapper;

import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioCreateRequest;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioResponse;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRestMapper {

    public Usuario toDomain(UsuarioCreateRequest request){
        return Usuario.builder()
                .usuarioUsuario(request.getUsuarioUsuario())
                .nombreUsuario(request.getNombreUsuario())
                .apellidoUsuario(request.getApellidoUsuario())
                .contrasenaHash(request.getContrasenaHash())
                .rolUsuario(request.getRol().name())
                .build();
    }

    public Usuario toDomain(UsuarioUpdateRequest request){
        return Usuario.builder()
                .usuarioUsuario(request.getUsuarioUsuario())
                .nombreUsuario(request.getNombreUsuario())
                .apellidoUsuario(request.getApellidoUsuario())
                .contrasenaHash(request.getContrasenaHash())
                .rolUsuario(request.getRol().name())
                .usuarioEstado(request.getUsuarioEstado())
                .build();
    }

    public UsuarioResponse toResponse(Usuario usuario){
        return UsuarioResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .usuarioUsuario(usuario.getUsuarioUsuario())
                .nombreUsuario(usuario.getNombreUsuario())
                .apellidoUsuario(usuario.getApellidoUsuario())
                .rolUsuario(usuario.getRolUsuario())
                .usuarioEstado(usuario.getUsuarioEstado())
                .activo(usuario.getActivo())
                .auditoriaFechaCreacion(usuario.getAuditoriasFechaCreacion())
                .build();
    }
}