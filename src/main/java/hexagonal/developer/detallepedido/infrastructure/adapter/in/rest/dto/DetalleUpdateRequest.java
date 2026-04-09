package hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleUpdateRequest {

    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;
}
