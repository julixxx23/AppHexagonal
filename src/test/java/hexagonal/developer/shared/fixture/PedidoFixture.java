package hexagonal.developer.shared.fixture;

import hexagonal.developer.pedido.dominio.model.EstadoPedido;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.shared.domain.model.PageDomain;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoFixture {

    public static Pedido unPedidoValido(){
        return Pedido.builder()
                .idPedido(1L)
                .fechaPedido(LocalDateTime.of(2024, 2, 12, 3, 8))
                .estadoPedido(EstadoPedido.CONFIRMADO)
                .activo(true)
                .usuario(UsuarioFixture.unUsuarioValido())
                .build();
    }

    public static Pedido unPedidoSinId(){
        return Pedido.builder()
                .fechaPedido(LocalDateTime.of(2026, 3, 21, 4, 6))
                .estadoPedido(EstadoPedido.ENTREGADO)
                .activo(true)
                .usuario(UsuarioFixture.unUsuarioSinId())
                .build();
    }
    public static Pedido unPedidoPendiente(){
        return Pedido.builder()
                .idPedido(1L)
                .fechaPedido(LocalDateTime.of(2024, 2, 12, 3, 8))
                .estadoPedido(EstadoPedido.PENDIENTE)
                .activo(true)
                .usuario(UsuarioFixture.unUsuarioValido())
                .build();
    }

    public static List<Pedido> unaListaDePedidos(){
        return List.of(unPedidoValido(), Pedido.builder()
                        .idPedido(1L)
                        .fechaPedido(LocalDateTime.of(2023, 3, 23, 4, 5))
                        .estadoPedido(EstadoPedido.CONFIRMADO)
                        .activo(true)
                        .usuario(UsuarioFixture.unUsuarioValido())
                       .build()
        );

        }
        public  static PageDomain<Pedido> unPageDomainDetalles(){
        return new PageDomain<>(unaListaDePedidos(), 0, 1,2L);
    }
}
