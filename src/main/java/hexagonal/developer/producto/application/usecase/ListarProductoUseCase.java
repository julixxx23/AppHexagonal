package hexagonal.developer.producto.application.usecase;

import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.producto.domain.port.in.ListarProductosPort;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListarProductoUseCase implements ListarProductosPort {

    private final ProductoRepositoryPort productoRepositoryPort;

    @Override
    public PageDomain<Producto> listarTodas(int pagina, int tamanio){
        return productoRepositoryPort.listarTodas(pagina, tamanio);
    }
}
