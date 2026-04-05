package hexagonal.developer.usuario.infrastructure.adapter.out.persistence.repository;

import hexagonal.developer.producto.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    Page<UsuarioEntity> findByNombreUsuarioContainingIgnoreCase(String nombre, Pageable pageable);

    Optional<UsuarioEntity> findByUsuarioUsuario(String usuarioUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuarioAndIdUsuarioNot(String nombreUsuario, Long idUsuario);
}