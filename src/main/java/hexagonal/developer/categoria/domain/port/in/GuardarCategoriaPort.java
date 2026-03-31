package hexagonal.developer.categoria.domain.port.in;

import hexagonal.developer.categoria.domain.model.Categoria;

public interface GuardarCategoriaPort {
    Categoria guardar(Categoria categoria);
}
