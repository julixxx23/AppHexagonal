package hexagonal.developer.categoria.domain.port.out;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.model.PageDomain;

import java.util.Optional;

public interface CategoriaRepositoryPort {
    Categoria guardar(Categoria categoria);
    Optional<Categoria> buscarPorId(Long id);
    PageDomain<Categoria> listarTodas(int pagina, int tamanio);
    boolean existePorNombre(String nombre);
}
