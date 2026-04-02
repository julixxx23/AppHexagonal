package hexagonal.developer.producto.domain.port.in;

import hexagonal.developer.producto.domain.model.Producto;

public interface BuscarProductoPort {
    Producto buscarPorId(Long id);
}
