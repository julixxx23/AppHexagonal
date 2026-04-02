package hexagonal.developer.categoria.application.usecase;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import hexagonal.developer.categoria.domain.port.in.ListarCategoriasPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListarCategoriasUseCase implements ListarCategoriasPort {
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    @Override
    public PageDomain<Categoria> listarTodas(int pagina, int tamanio){
        return categoriaRepositoryPort.listarTodas(pagina, tamanio);
    }
}
