package hexagonal.developer.categoria.infrastructure.adapter.out.persistence.repository;

import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdCategoriaIsNot(String nombre, Long idCategoria);
    Page<CategoriaEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}