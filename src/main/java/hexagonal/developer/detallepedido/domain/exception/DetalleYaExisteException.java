package hexagonal.developer.detallepedido.domain.exception;

public class DetalleYaExisteException extends RuntimeException{
    public DetalleYaExisteException(String texto){
        super(texto);
    }
}
