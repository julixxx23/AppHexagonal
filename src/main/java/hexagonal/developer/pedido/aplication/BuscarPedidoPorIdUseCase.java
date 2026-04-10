package hexagonal.developer.pedido.aplication;

import hexagonal.developer.pedido.dominio.exception.PedidoNotFoundException;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.in.BuscarPedidoPorIdPort;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarPedidoPorIdUseCase implements BuscarPedidoPorIdPort {
    private final PedidoRepositoryPort pedidoRepositoryPort;

    @Override
    public Pedido buscarPorId(Long id){
        return pedidoRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));
    }


}
