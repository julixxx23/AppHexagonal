package hexagonal.developer.detallepedido.domain.port.in;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.shared.domain.model.PageDomain;

public interface ListarDetallesPort {
    PageDomain<DetallePedido> listar(int pagina, int tamanio);
}
