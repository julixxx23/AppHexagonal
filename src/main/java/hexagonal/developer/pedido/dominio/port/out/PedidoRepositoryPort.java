package hexagonal.developer.pedido.dominio.port.out;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.shared.domain.model.PageDomain;

import java.util.Optional;

public interface PedidoRepositoryPort {
    Pedido guardar (Pedido pedido);
    Pedido actualizar(Long id, Pedido pedido);
    Optional<Pedido> buscarPorId(Long id);
    Optional<Pedido> avanzarPedido(Long id);
    Optional<Pedido> cancelar(Long id);
    PageDomain<Pedido> buscarPorTexto(String texto, int pagina, int tamanio);
    PageDomain<Pedido> listar(int pagina, int tamanio);
    boolean existeYEstaActivo(Pedido pedido);

}
