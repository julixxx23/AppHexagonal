package hexagonal.developer.producto.domain.exception;

import hexagonal.developer.shared.domain.exception.NotFoundException;

public class ProductoNotFoundException extends NotFoundException {
    public ProductoNotFoundException(Long id) {
        super("Producto no encontrado con id: " + id);
    }
}