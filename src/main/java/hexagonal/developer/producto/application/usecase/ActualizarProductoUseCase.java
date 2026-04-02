package hexagonal.developer.producto.application.usecase;

import hexagonal.developer.producto.domain.exception.ProductoNotFoundException;
import hexagonal.developer.producto.domain.exception.ProductoYaExisteException;
import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.domain.port.in.ActualizarProductoPort;
import hexagonal.developer.producto.domain.port.in.BuscarProductoPort;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActualizarProductoUseCase implements ActualizarProductoPort {

    private final ProductoRepositoryPort productoRepositoryPort;
    @Override
    public Producto actualizar(Long id, Producto producto) {
        productoRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        if (productoRepositoryPort.existePorNombreExcluyendoId(producto.getNombre(), id)) {
            throw new ProductoYaExisteException("Ya existe un producto con ese nombre");
        }

        return productoRepositoryPort.actualizar(id, producto);
    }
}
