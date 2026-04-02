package hexagonal.developer.producto.application.usecase;

import hexagonal.developer.producto.domain.exception.ProductoNotFoundException;
import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.domain.port.in.BuscarProductoPort;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarProductoUseCase implements BuscarProductoPort {

    private final ProductoRepositoryPort productoRepositoryPort;

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }
}