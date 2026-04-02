package hexagonal.developer.producto.domain.port.in;

import hexagonal.developer.producto.domain.model.Producto;

public interface GuardarProductoPort {
    Producto guardar(Producto producto);
}
