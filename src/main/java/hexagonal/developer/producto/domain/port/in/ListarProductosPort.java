package hexagonal.developer.producto.domain.port.in;

import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.shared.domain.model.PageDomain;

public interface ListarProductosPort {
    PageDomain<Producto> listarTodas(int pagina, int tamanio);

}
