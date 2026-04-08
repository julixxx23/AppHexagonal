package hexagonal.developer.pedido.infrastructure.adapter.in.rest.dto;

import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioResponseSimple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {
    private Long idPedido;
    private LocalDateTime fechaPedido;
    private String estadoPedido;
    private Boolean activo;
    private UsuarioResponseSimple usuarioResponseSimple;
}
