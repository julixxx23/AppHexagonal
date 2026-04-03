package hexagonal.developer.categoria.infrastructure.adapter.out.persistence.repository;

import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import hexagonal.developer.producto.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, Long> {
    boolean existsByNombre(String nombre);
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);
    Page<CategoriaEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
