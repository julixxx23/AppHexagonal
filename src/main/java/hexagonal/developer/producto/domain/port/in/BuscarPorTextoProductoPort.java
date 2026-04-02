package hexagonal.developer.producto.domain.port.in;

import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.shared.domain.model.PageDomain;

public interface BuscarPorTextoProductoPort {
    PageDomain<Producto> buscarPorTexto(String texto, int pagina, int tamanio);
}
