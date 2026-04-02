package hexagonal.developer.producto.application.usecase;

import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.domain.port.in.GuardarProductoPort;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GuardarProductoUseCase implements GuardarProductoPort {

    private final ProductoRepositoryPort productoRepositoryPort;

    @Override
    public Producto guardar(Producto producto){
        if(productoRepositoryPort.existePorNombre(producto.getNombre())){
            throw  new IllegalArgumentException("Ya existe un producto con ese nombre");
        }
        return productoRepositoryPort.guardar(producto);
    }

}
