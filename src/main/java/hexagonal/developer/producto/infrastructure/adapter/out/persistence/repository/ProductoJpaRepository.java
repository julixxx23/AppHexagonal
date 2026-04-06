package hexagonal.developer.producto.infrastructure.adapter.out.persistence.repository;

import hexagonal.developer.producto.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCaseAndIdProductoNot(String nombre, Long idProducto);

    Page<ProductoEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}