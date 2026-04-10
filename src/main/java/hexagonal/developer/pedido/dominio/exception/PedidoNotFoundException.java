package hexagonal.developer.pedido.dominio.exception;

public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException(Long id){
        super("No encontrado por id: " + id);
    }
}
