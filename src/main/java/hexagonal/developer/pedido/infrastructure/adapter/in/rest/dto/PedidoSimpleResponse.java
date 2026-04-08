package hexagonal.developer.pedido.infrastructure.adapter.in.rest.dto;

import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioResponseSimple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoSimpleResponse {
    private Long idPedido;
    private UsuarioResponseSimple usuario;
}
