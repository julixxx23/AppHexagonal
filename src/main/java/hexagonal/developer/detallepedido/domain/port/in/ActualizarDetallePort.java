package hexagonal.developer.detallepedido.domain.port.in;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;

public interface ActualizarDetallePort {
    DetallePedido actualizar(Long id, DetallePedido detallePedido);
}
