package hexagonal.developer.categoria.application.usecase;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.port.in.GuardarCategoriaPort;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GuardarCategoriaUseCase implements GuardarCategoriaPort {

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    @Override
    public Categoria guardar(Categoria categoria) {
        if (categoriaRepositoryPort.existePorNombre(categoria.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }
        return categoriaRepositoryPort.guardar(categoria);
    }
}