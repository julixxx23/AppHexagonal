package hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.repository;

import hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.entity.DetallePedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetalleJpaRepository extends JpaRepository<DetallePedidoEntity, Long> {

    boolean existsByPedidoIdPedidoAndProductoIdProducto(Long idPedido, Long idProducto);
}