package hexagonal.developer.categoria.domain.port.in;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.model.PageDomain;


public interface ListarCategoriasPort {
    PageDomain<Categoria> listarTodas(int pagina, int tamanio);
}
