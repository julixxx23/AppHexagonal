package hexagonal.developer.usuario.domain.exception;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String mensaje) {
        super(mensaje);
}
}
