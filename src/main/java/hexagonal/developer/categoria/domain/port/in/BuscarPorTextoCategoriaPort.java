package hexagonal.developer.categoria.domain.port.in;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.shared.domain.model.PageDomain;

public interface BuscarPorTextoCategoriaPort {
    PageDomain<Categoria> buscarPorTexto(String texto, int pagina, int tamanio);

}
