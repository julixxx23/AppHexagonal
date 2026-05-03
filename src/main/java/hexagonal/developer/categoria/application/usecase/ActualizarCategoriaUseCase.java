package hexagonal.developer.categoria.application.usecase;

import hexagonal.developer.categoria.domain.exception.CategoriaNotFoundException;
import hexagonal.developer.categoria.domain.exception.CategoriaYaExisteException;
import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.port.in.ActualizarCategoriaPort;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActualizarCategoriaUseCase implements ActualizarCategoriaPort{

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    //transcribe metodo
    @Override
    public Categoria actualizar(Long id, Categoria categoria){
        categoriaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        if(categoriaRepositoryPort.existePorNombreExcluyendoId(categoria.getNombre(), id)){
            throw new CategoriaYaExisteException("Ya existe una categoria con ese nombre");

        }
        return categoriaRepositoryPort.actualizar(id, categoria);
    }
}
