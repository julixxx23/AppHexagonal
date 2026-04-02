package hexagonal.developer.producto.application.usecase;

import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.domain.port.in.BuscarPorTextoProductoPort;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import hexagonal.developer.shared.domain.model.PageDomain;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarPorTextoProductoUseCase implements BuscarPorTextoProductoPort {

    private final ProductoRepositoryPort productoRepositoryPort;

    @Override
    public PageDomain<Producto> buscarPorTexto(String texto, int pagina, int tamanio){
        return productoRepositoryPort.buscarPorTexto(texto, pagina, tamanio);
    }
}
