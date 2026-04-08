package hexagonal.developer.pedido.dominio.exception;

public class UsuarioNoActivoException extends RuntimeException {
    public UsuarioNoActivoException(String texto){
        super(texto);
    }
}
