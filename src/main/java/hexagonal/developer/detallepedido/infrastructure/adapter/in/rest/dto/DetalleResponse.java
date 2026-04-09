package hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto;

import hexagonal.developer.pedido.infrastructure.adapter.in.rest.dto.PedidoSimpleResponse;
import hexagonal.developer.producto.infrastructure.adapter.in.rest.dto.ProductoSimpleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleResponse {
    private Long idDetalle;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subTotal;
    private PedidoSimpleResponse pedido;
    private ProductoSimpleResponse producto;
}
