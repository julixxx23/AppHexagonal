package hexagonal.developer.categoria.domain.exception;

public class CategoriaYaExisteException extends RuntimeException {
    public CategoriaYaExisteException(String mensaje) {
        super(mensaje);
    }
}