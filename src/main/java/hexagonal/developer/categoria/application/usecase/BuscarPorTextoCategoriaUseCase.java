package hexagonal.developer.categoria.application.usecase;


import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.port.in.BuscarPorTextoCategoriaPort;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import hexagonal.developer.shared.domain.model.PageDomain;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarPorTextoCategoriaUseCase implements BuscarPorTextoCategoriaPort{

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    @Override
    public PageDomain<Categoria> buscarPorTexto(String texto, int pagina, int tamanio){
        return categoriaRepositoryPort.buscarPorTexto(texto, pagina, tamanio);
    }


}
