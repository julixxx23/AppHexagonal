package hexagonal.developer.categoria.infrastructure.adapter.out.persistence.mapper;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaPersistenceMapper {

    public Categoria toDomain(CategoriaEntity entity) {
        return Categoria.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .fechaCreacion(entity.getFechaCreacion())
                .build();
    }

    public CategoriaEntity toEntity(Categoria categoria) {
        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(categoria.getId());
        entity.setNombre(categoria.getNombre());
        entity.setDescripcion(categoria.getDescripcion());
        entity.setActivo(categoria.getActivo());
        return entity;
    }
}