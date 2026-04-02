package hexagonal.developer.producto.domain.port.in;

import hexagonal.developer.producto.domain.model.Producto;

public interface ActualizarProductoPort {
    Producto actualizar(Long id, Producto producto);
}
