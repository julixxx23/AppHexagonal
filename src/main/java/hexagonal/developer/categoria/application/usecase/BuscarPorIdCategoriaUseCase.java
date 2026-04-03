package hexagonal.developer.categoria.application.usecase;

import hexagonal.developer.categoria.domain.exception.CategoriaNotFoundException;
import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import hexagonal.developer.categoria.domain.port.in.BuscarPorIdCategoriaPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarPorIdCategoriaUseCase implements BuscarPorIdCategoriaPort {

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    @Override
    public Categoria buscarPorId(Long id){
        return categoriaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }
}
