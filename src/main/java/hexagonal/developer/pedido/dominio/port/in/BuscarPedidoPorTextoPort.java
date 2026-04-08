package hexagonal.developer.pedido.dominio.port.in;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.shared.domain.model.PageDomain;

public interface BuscarPedidoPorTextoPort {
    PageDomain<Pedido> buscarPorTexto(String texto, int pagina, int tamanio);
}
