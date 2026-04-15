package hexagonal.developer.detallepedido.domain.model;

import hexagonal.developer.detallepedido.domain.exception.ElDetalleCantidadEsObligatorio;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.producto.domain.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {
    private Long idDetalle;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private  BigDecimal subTotal;
    private Pedido pedido;
    private Producto producto;

    public BigDecimal calcularSubTotal(){
        if(cantidad == null || cantidad <= 0){
            throw  new ElDetalleCantidadEsObligatorio("La cantidad es obligatoria y debe ser mayor a cero");
        }
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));

    }



}
