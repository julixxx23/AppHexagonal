package hexagonal.developer.categoria.domain.port.in;

import hexagonal.developer.categoria.domain.model.Categoria;

public interface ActualizarCategoriaPort {
    Categoria actualizar(Long id, Categoria categoria);
}
