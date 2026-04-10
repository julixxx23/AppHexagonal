package hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCreateRequest {

    @NotNull(message = "El id del producto es requerido")
    private Long idProducto;

    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;


}
