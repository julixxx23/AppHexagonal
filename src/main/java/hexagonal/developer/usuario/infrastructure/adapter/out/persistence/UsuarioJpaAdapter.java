package hexagonal.developer.usuario.infrastructure.adapter.out.persistence;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.mapper.UsuarioPersistenceMapper;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioPersistenceMapper mapper;

    @Override
    public Usuario guardar(Usuario usuario) {
        return mapper.toDomain(usuarioJpaRepository.save(mapper.toEntity(usuario)));
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        return mapper.toDomain(usuarioJpaRepository.save(mapper.toEntity(usuario)));
    }
    @Override
    public Usuario estado(Long id, Boolean estado){
        UsuarioEntity entity = usuarioJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado por id"));
        entity.setActivo(estado);
        return mapper.toDomain(usuarioJpaRepository.save(entity));
    }


    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public PageDomain<Usuario> listarTodos(int pagina, int tamanio) {
        Page<UsuarioEntity> entityPage = usuarioJpaRepository.findAll(PageRequest.of(pagina, tamanio));
        Page<Usuario> page = entityPage.map(mapper::toDomain);
        return new PageDomain<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public PageDomain<Usuario> buscarPorTexto(String texto, int pagina, int tamanio) {
        Page<UsuarioEntity> entityPage = usuarioJpaRepository
                .findByNombreUsuarioContainingIgnoreCase(texto, PageRequest.of(pagina, tamanio));
        Page<Usuario> page = entityPage.map(mapper::toDomain);
        return new PageDomain<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public void eliminar(Long id) {
        usuarioJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorUsuarioUsuario(String usuarioUsuario) {
        return usuarioJpaRepository.existsByUsuarioUsuario(usuarioUsuario); // 👈
    }

    @Override
    public boolean existePorUsuarioUsuarioExcluyendoId(String usuarioUsuario, Long id) {
        return usuarioJpaRepository.existsByUsuarioUsuarioAndIdUsuarioNot(usuarioUsuario, id);
    }
}