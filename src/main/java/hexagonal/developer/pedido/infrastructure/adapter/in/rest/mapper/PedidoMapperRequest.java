package hexagonal.developer.pedido.infrastructure.adapter.in.rest.mapper;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.infrastructure.adapter.in.rest.dto.PedidoCreateRequest;
import hexagonal.developer.pedido.infrastructure.adapter.in.rest.dto.PedidoResponse;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioResponseSimple;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapperRequest {

    public Pedido toDomain(PedidoCreateRequest request){
        return Pedido.builder()
                .usuario(Usuario.builder()
                        .idUsuario(request.getIdUsuario())
                        .build())
                .build();
    }

    public PedidoResponse toResponse(Pedido pedido){
        return PedidoResponse.builder()
                .idPedido(pedido.getIdPedido())
                .fechaPedido(pedido.getFechaPedido())
                .estadoPedido(pedido.getEstadoPedido().name())
                .activo(pedido.getActivo())
                .usuarioResponseSimple(UsuarioResponseSimple.builder()
                        .username(pedido.getUsuario().getUsuarioUsuario())
                        .nombre(pedido.getUsuario().getNombreUsuario())
                        .apellido(pedido.getUsuario().getApellidoUsuario())
                        .build())
                .build();
    }
}
