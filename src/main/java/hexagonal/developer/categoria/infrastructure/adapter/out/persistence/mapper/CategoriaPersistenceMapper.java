package hexagonal.developer.categoria.infrastructure.adapter.out.persistence.mapper;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaPersistenceMapper {

    public Categoria toDomain(CategoriaEntity categoriaEntity){
        return Categoria.builder()
                .id(categoriaEntity.getId())
                .nombre(categoriaEntity.getNombre())
                .descripcion(categoriaEntity.getDescripcion())
                .activo(categoriaEntity.getActivo())
                .fechaCreacion(categoriaEntity.getFechaCreacion())
                .build();
    }

    public CategoriaEntity toEntity(Categoria categoria){
        return CategoriaEntity.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .activo(categoria.getActivo())
                .fechaCreacion(categoria.getFechaCreacion())
                .build();

    }
}
