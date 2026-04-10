package hexagonal.developer.pedido.aplication;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.in.BuscarPedidoPorTextoPort;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import hexagonal.developer.shared.domain.model.PageDomain;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarPedidoPorTextoUseCase implements BuscarPedidoPorTextoPort {
    private final PedidoRepositoryPort pedidoRepositoryPort;

    @Override
    public PageDomain<Pedido> buscarPorTexto(String texto, int pagina, int tamanio){
        return pedidoRepositoryPort.buscarPorTexto(texto, pagina, tamanio);
    }
}
