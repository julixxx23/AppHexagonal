package hexagonal.developer.pedido.dominio.model;

import hexagonal.developer.usuario.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private Long idPedido;
    private LocalDateTime fechaPedido;

    @Builder.Default
    private EstadoPedido estadoPedido = EstadoPedido.PENDIENTE;

    @Builder.Default
    private Boolean activo = true;

    private Usuario usuario;


    public void avanzarEstado() {
        switch (estadoPedido) {
            case PENDIENTE  -> estadoPedido = EstadoPedido.CONFIRMADO;
            case CONFIRMADO -> estadoPedido = EstadoPedido.EN_PROCESO;
            case EN_PROCESO -> estadoPedido = EstadoPedido.ENVIADO;
            case ENVIADO    -> estadoPedido = EstadoPedido.ENTREGADO;
            default -> throw new IllegalStateException(
                    "Cannot advance from state: " + estadoPedido
            );
        }
    }

    public void cancelar() {
        if (estadoPedido == EstadoPedido.ENTREGADO) {
            throw new IllegalStateException(
                    "Cannot cancel a delivered order"
            );
        }
        estadoPedido = EstadoPedido.CANCELADO;
    }

    public void validarEditable() {
        if (this.estadoPedido != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("Order can only be modified in PENDING state");
        }
    }
}