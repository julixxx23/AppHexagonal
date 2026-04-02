package hexagonal.developer.producto.infrastructure.adapter.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoSimpleResponse {
    private Long idProducto;
    private String nombre;
}
