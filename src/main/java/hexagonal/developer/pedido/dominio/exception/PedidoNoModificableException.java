package hexagonal.developer.pedido.dominio.exception;

public class PedidoNoModificableException extends RuntimeException {
    public PedidoNoModificableException(String mensaje) {
        super(mensaje);
    }
}

