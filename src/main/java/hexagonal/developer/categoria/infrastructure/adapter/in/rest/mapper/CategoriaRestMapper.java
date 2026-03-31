package hexagonal.developer.categoria.infrastructure.adapter.in.rest.mapper;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.infrastructure.adapter.in.rest.dto.CategoriaRequest;
import hexagonal.developer.categoria.infrastructure.adapter.in.rest.dto.CategoriaResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoriaRestMapper {

    public Categoria toDomain(CategoriaRequest request) {
        return Categoria.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .activo(true)
                .build();
    }

    public CategoriaResponse toResponse(Categoria categoria) {
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .activo(categoria.getActivo())
                .fechaCreacion(categoria.getFechaCreacion())
                .build();
    }
}
