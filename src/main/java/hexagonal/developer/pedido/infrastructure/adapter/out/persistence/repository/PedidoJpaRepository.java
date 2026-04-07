package hexagonal.developer.pedido.infrastructure.adapter.out.persistence.repository;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.infrastructure.adapter.out.persistence.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {
    boolean existeYEstaActivo(Pedido pedido);
    Page<PedidoEntity> findByEstadoPedidoContainingIgnoreCase(String texto, Pageable pageable);
}
