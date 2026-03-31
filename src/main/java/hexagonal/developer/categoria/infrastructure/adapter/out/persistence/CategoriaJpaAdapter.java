package hexagonal.developer.categoria.infrastructure.adapter.out.persistence;

import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.model.PageDomain;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.mapper.CategoriaPersistenceMapper;
import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.repository.CategoriaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;


import java.util.Optional;

@RequiredArgsConstructor
public class CategoriaJpaAdapter implements CategoriaRepositoryPort {

    private final CategoriaJpaRepository jpaRepository;
    private final CategoriaPersistenceMapper mapper;

    @Override
    public Categoria guardar(Categoria categoria) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(categoria)));
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public PageDomain<Categoria> listarTodas(int pagina, int tamanio) {
        Page<Categoria> page = jpaRepository
                .findAll(PageRequest.of(pagina, tamanio))
                .map(mapper::toDomain);

        return new PageDomain<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return jpaRepository.existsByNombre(nombre);
    }
}