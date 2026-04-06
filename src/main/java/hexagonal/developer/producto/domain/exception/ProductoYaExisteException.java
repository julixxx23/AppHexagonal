package hexagonal.developer.producto.domain.exception;

public class ProductoYaExisteException extends RuntimeException {
    public ProductoYaExisteException(String mensaje) {
        super(mensaje);
    }
}