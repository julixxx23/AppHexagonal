package hexagonal.developer.pedido.dominio.port.in;

import hexagonal.developer.pedido.dominio.model.Pedido;

public interface ActualizarPedidoPort {
    Pedido actualizar(Long id, Pedido pedido);
}
