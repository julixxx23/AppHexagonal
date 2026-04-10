package hexagonal.developer.producto.infrastructure.adapter.in.rest.mapper;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.infrastructure.adapter.in.rest.dto.CategoriaSimpleResponse;
import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.infrastructure.adapter.in.rest.dto.ProductoCreateRequest;
import hexagonal.developer.producto.infrastructure.adapter.in.rest.dto.ProductoResponse;
import hexagonal.developer.producto.infrastructure.adapter.in.rest.dto.ProductoUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductoRestMapper {

    public Producto toDomain(ProductoCreateRequest request) {
        return Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .activo(request.getActivo())
                .categoria(Categoria.builder()
                        .idCategoria(request.getCategoriaId())
                        .build())
                .build();
    }

    public Producto toDomain(ProductoUpdateRequest request) {
        return Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .activo(request.getActivo())
                .categoria(Categoria.builder()
                        .idCategoria(request.getCategoriaId())
                        .build())
                .build();
    }

    public ProductoResponse toResponse(Producto producto) {
        return ProductoResponse.builder()
                .id(producto.getIdProducto())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .activo(producto.getActivo())
                .fechaCreacion(producto.getFechaCreacion())
                .categoria(CategoriaSimpleResponse.builder()
                        .id(producto.getCategoria().getIdCategoria())
                        .nombre(producto.getCategoria().getNombre())
                        .build())
                .build();
    }
}