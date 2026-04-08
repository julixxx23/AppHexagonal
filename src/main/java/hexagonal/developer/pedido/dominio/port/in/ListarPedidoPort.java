package hexagonal.developer.pedido.dominio.port.in;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.shared.domain.model.PageDomain;

public interface ListarPedidoPort {
    PageDomain<Pedido> listar(int pagina, int tamanio);
}
