package hexagonal.developer.detallepedido.domain.port.in;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;

public interface BuscarDetallePorIdPort {
    DetallePedido buscarPorId(Long id);
}
