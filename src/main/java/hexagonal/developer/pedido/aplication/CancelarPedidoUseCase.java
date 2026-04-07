package hexagonal.developer.pedido.aplication;

import hexagonal.developer.pedido.dominio.exception.PedidoNotFoundException;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.in.CancelarPedidoPort;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CancelarPedidoUseCase implements CancelarPedidoPort {
    private final PedidoRepositoryPort pedidoRepositoryPort;

    @Override
    public Pedido cancelar(Long id) {
        Pedido pedido = pedidoRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));

        pedido.cancelar();

        return pedidoRepositoryPort.guardar(pedido);
    }
}