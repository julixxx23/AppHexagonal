package hexagonal.developer.producto.domain.port.out;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.producto.domain.model.Producto;

import java.util.Optional;

public interface ProductoRepositoryPort {
    Producto guardar(Producto producto);
    Optional<Producto> buscarPorId(Long id);
    PageDomain<Producto> listarTodas(int pagina, int tamanio);
    PageDomain<Producto> buscarPorTexto(String texto, int pagina, int tamanio);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
    boolean existePorNombreExcluyendoId(String nombre, Long id);

}
