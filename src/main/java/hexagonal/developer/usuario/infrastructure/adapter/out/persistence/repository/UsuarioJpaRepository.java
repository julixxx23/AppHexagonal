package hexagonal.developer.usuario.infrastructure.adapter.out.persistence.repository;

import hexagonal.developer.producto.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    boolean existePorNombre(String nombre);
    boolean existePorNombreExcluyendoId(String nombre, Long id);
    Page<UsuarioEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
