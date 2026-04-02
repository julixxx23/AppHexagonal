package hexagonal.developer.producto.infrastructure.adapter.in.rest.dto;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.infrastructure.adapter.in.rest.dto.CategoriaSimpleResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoUpdateRequest {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(max = 500, message = "La descripcion no puede superar los 500 caracteres")
    private String descripcion;

    private BigDecimal precio;
    private Integer stock;
    private Boolean activo;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoriaId;

}
