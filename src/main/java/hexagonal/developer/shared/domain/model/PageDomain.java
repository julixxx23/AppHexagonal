package hexagonal.developer.shared.domain.model;

import java.util.List;

public record PageDomain<T>(
    List<T> contenido,
    int paginaActual,
    int totalPaginas,
    long totalElementos

){}
