package hexagonal.developer.producto.infrastructure.adapter.out.persistence.mapper;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoPersistenceMapper {

    public Producto toDomain(ProductoEntity entity) {
        Categoria categoria = Categoria.builder()
                .id(entity.getCategoria().getId())
                .nombre(entity.getCategoria().getNombre())
                .descripcion(entity.getCategoria().getDescripcion())
                .activo(entity.getCategoria().getActivo())
                .fechaCreacion(entity.getCategoria().getFechaCreacion())
                .build();

        return Producto.builder()
                .idProducto(entity.getIdProducto())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .stock(entity.getStock())
                .activo(entity.getActivo())
                .fechaCreacion(entity.getCategoria().getFechaCreacion())
                .categoria(categoria)
                .build();
    }

    public ProductoEntity toEntity(Producto producto) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(producto.getCategoria().getId());

        ProductoEntity entity = new ProductoEntity();
        entity.setIdProducto(producto.getIdProducto());
        entity.setNombre(producto.getNombre());
        entity.setDescripcion(producto.getDescripcion());
        entity.setPrecio(producto.getPrecio());
        entity.setStock(producto.getStock());
        entity.setActivo(producto.getActivo());
        entity.setCategoria(categoriaEntity);
        return entity;
    }
}