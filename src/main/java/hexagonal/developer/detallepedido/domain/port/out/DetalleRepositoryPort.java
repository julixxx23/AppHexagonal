package hexagonal.developer.detallepedido.domain.port.out;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.shared.domain.model.PageDomain;

import java.util.Optional;

public interface DetalleRepositoryPort {
    DetallePedido guardar(DetallePedido detallePedido);
    DetallePedido actualizar(Long id, DetallePedido detallePedido);
    Optional<DetallePedido> buscarPorId(Long id);
    PageDomain<DetallePedido> listar(int pagina, int tamanio);
    void eliminar(Long id);
    boolean existePorPedidoYProducto(Long idPedido, Long idProducto);
}
