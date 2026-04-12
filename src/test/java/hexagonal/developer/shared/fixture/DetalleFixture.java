package hexagonal.developer.shared.fixture;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.shared.domain.model.PageDomain;

import java.math.BigDecimal;
import java.util.List;

public class DetalleFixture {

    public static DetallePedido undetalleValido() {
        return DetallePedido.builder()
                .idDetalle(1L)
                .cantidad(2)
                .precioUnitario(BigDecimal.valueOf(50))
                .subTotal(BigDecimal.valueOf(100))
                .pedido(PedidoFixture.unPedidoValido())
                .producto(ProductoFixture.unProductoValido())
                .build();
    }

    public static  DetallePedido unDetalleSinId(){
        return DetallePedido.builder()
                .cantidad(5)
                .precioUnitario(BigDecimal.valueOf(50))
                .subTotal(BigDecimal.valueOf(250))
                .pedido(PedidoFixture.unPedidoPendiente())
                .producto(ProductoFixture.unProductoValido())
                .build();
    }

    public static List<DetallePedido> unaListaDeDetalles() {
        return List.of(undetalleValido(), DetallePedido.builder()
                .idDetalle(1L)
                .cantidad(3)
                .precioUnitario(BigDecimal.valueOf(150))
                .subTotal(BigDecimal.valueOf(450))
                .pedido(PedidoFixture.unPedidoValido())
                .producto(ProductoFixture.unProductoValido())
                .build()
        );

        }
    public static PageDomain<DetallePedido> unPageDomainDetalles(){
        return new PageDomain<>(unaListaDeDetalles(), 0, 1, 2L);

    }
}
