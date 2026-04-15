package hexagonal.developer.detallepedido.domain.exception;

public class ElDetalleCantidadEsObligatorio extends RuntimeException{
    public ElDetalleCantidadEsObligatorio(String mensaje){
        super(mensaje);
    }
}
