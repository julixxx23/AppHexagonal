package hexagonal.developer.detallepedido.infrastructure.adapter.in.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleResponseSimple {

    private Long id;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subTotal;
}
