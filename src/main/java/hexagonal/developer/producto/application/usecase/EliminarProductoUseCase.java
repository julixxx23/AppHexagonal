package hexagonal.developer.producto.application.usecase;

import hexagonal.developer.producto.domain.exception.ProductoNotFoundException;
import hexagonal.developer.producto.domain.port.in.BuscarProductoPort;
import hexagonal.developer.producto.domain.port.in.EliminarProductoPort;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EliminarProductoUseCase implements EliminarProductoPort {

    private final ProductoRepositoryPort productoRepositoryPort;

    @Override
    public void eliminar(Long id) {
        productoRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        productoRepositoryPort.eliminar(id);
    }
}
