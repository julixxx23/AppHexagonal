package hexagonal.developer.detallepedido.domain.port.in;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;

public interface BuscarDetallePorId {
    DetallePedido buscarPorId(Long id);
}
