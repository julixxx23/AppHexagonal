package hexagonal.developer.usuario.domain.exception;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String nombre){
        super("Ya existe un usuario con ese nombre: " + nombre);
    }
}
