package hexagonal.developer.categoria.application.usecase;

import hexagonal.developer.categoria.domain.exception.CategoriaNotFoundException;
import hexagonal.developer.categoria.domain.port.in.EliminarCategoriaPort;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EliminarCategoriaUseCase implements EliminarCategoriaPort{

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    @Override
    public void eliminar(Long id){
        categoriaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        categoriaRepositoryPort.eliminar(id);
    }
}
