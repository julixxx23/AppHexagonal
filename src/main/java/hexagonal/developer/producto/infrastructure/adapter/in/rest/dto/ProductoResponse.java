package hexagonal.developer.producto.infrastructure.adapter.in.rest.dto;

import hexagonal.developer.categoria.infrastructure.adapter.in.rest.dto.CategoriaSimpleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private CategoriaSimpleResponse categoria;
}
