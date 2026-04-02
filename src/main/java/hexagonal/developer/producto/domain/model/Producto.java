package hexagonal.developer.producto.domain.model;


import hexagonal.developer.categoria.domain.model.Categoria;
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
public class Producto {

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private Categoria categoria;
}
