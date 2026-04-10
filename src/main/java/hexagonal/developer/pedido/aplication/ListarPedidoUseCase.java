package hexagonal.developer.pedido.aplication;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.in.ListarPedidoPort;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import hexagonal.developer.shared.domain.model.PageDomain;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListarPedidoUseCase implements ListarPedidoPort {
    private final PedidoRepositoryPort pedidoRepositoryPort;

    @Override
    public PageDomain<Pedido> listar(int pagina, int tamanio){
        return pedidoRepositoryPort.listar(pagina, tamanio);
    }
}
