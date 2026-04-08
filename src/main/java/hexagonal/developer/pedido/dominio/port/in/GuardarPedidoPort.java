package hexagonal.developer.pedido.dominio.port.in;

import hexagonal.developer.pedido.dominio.model.Pedido;

public interface GuardarPedidoPort {
    Pedido guardar(Pedido pedido);
}
