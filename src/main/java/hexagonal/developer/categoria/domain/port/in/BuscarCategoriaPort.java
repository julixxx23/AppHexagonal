package hexagonal.developer.categoria.domain.port.in;

import hexagonal.developer.categoria.domain.model.Categoria;

public interface BuscarCategoriaPort {
    Categoria buscarPorId(Long id);


}
