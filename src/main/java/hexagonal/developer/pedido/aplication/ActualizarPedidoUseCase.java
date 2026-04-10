package hexagonal.developer.pedido.aplication;

import hexagonal.developer.pedido.dominio.exception.PedidoNotFoundException;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.in.ActualizarPedidoPort;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActualizarPedidoUseCase implements ActualizarPedidoPort {
    private final PedidoRepositoryPort pedidoRepositoryPort;

    @Override
    public Pedido actualizar(Long id, Pedido pedido) {
        Pedido pedidoExistente = pedidoRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));

        pedidoExistente.validarEditable();

        return pedidoRepositoryPort.guardar(pedido);
    }
}