package hexagonal.developer.categoria.infrastructure.adapter.out.persistence.repository;

import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, Long> {
    boolean existsByNombre(String nombre);
}
