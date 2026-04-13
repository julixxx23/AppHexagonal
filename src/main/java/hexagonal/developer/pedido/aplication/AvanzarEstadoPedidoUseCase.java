package hexagonal.developer.pedido.aplication;

import hexagonal.developer.pedido.dominio.exception.PedidoNotFoundException;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.in.AvanzarEstadoPedidoPort;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvanzarEstadoPedidoUseCase implements AvanzarEstadoPedidoPort {
    private final PedidoRepositoryPort pedidoRepositoryPort;

    @Override
    public Pedido avanzarEstado(Long id) {
        Pedido pedido = pedidoRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));

        pedido.avanzarEstado();

        return pedidoRepositoryPort.avanzarEstado(pedido);
    }
}