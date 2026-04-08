package hexagonal.developer.detallepedido.domain.exception;

public class PedidoNoModificableException extends RuntimeException {
    public PedidoNoModificableException(String mensaje) {
        super(mensaje);
    }
}