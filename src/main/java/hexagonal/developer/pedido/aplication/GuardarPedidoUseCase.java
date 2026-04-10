package hexagonal.developer.pedido.aplication;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.in.GuardarPedidoPort;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GuardarPedidoUseCase  implements GuardarPedidoPort {
    private final PedidoRepositoryPort pedidoRepositoryPort;

    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRepositoryPort.guardar(pedido);
    }
}
