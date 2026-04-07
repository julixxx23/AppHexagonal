package hexagonal.developer.pedido.infrastructure.adapter.out.persistence.mapper;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.infrastructure.adapter.out.persistence.entity.PedidoEntity;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class PedidoPersistenceMapper {

    public Pedido toDomain(PedidoEntity entity){
        Usuario usuario = Usuario.builder()
                .idUsuario(entity.getUsuario().getIdUsuario())
                .usuarioUsuario(entity.getUsuario().getUsuarioUsuario())
                .nombreUsuario(entity.getUsuario().getNombreUsuario())
                .apellidoUsuario(entity.getUsuario().getApellidoUsuario())
                .rolUsuario(entity.getUsuario().getRolUsuario().name())
                .activo(entity.getUsuario().getActivo())
                .auditoriasFechaCreacion(entity.getUsuario().getAuditoriaFechaCreacion())
                .build();

        return Pedido.builder()
                .idPedido(entity.getIdPedido())
                .fechaPedido(entity.getFechaPedido())
                .estadoPedido(entity.getEstadoPedido())
                .activo(entity.getActivo())
                .usuario(usuario)
                .build();

    }

    public PedidoEntity toEntity(Pedido pedido){
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdUsuario(pedido.getUsuario().getIdUsuario());

        PedidoEntity entity = new PedidoEntity();
        entity.setIdPedido(pedido.getIdPedido());
        entity.setFechaPedido(pedido.getFechaPedido());
        entity.setEstadoPedido(pedido.getEstadoPedido());
        entity.setActivo(pedido.getActivo());
        entity.setUsuario(usuarioEntity);
        return entity;

    }
}
