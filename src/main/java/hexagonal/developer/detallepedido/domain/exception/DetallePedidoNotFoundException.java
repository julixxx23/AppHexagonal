package hexagonal.developer.detallepedido.domain.exception;


public class DetallePedidoNotFoundException extends RuntimeException {
    public DetallePedidoNotFoundException(Long id){
        super("Detalle Pedido no encontrado por id: " + id);
    }
}
