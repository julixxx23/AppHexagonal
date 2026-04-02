package hexagonal.developer.producto.infrastructure.adapter.out.persistence;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import hexagonal.developer.producto.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import hexagonal.developer.producto.infrastructure.adapter.out.persistence.mapper.ProductoPersistenceMapper;
import hexagonal.developer.producto.infrastructure.adapter.out.persistence.repository.ProductoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductoJpaAdapter implements ProductoRepositoryPort {

    private final ProductoJpaRepository productoJpaRepository;
    private final ProductoPersistenceMapper mapper;

    @Override
    public Producto guardar(Producto producto) {
        return mapper.toDomain(productoJpaRepository.save(mapper.toEntity(producto)));
    }

    @Override
    public Producto actualizar(Long id, Producto producto){
        return mapper.toDomain(productoJpaRepository.save(mapper.toEntity(producto)));
    }


    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return productoJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public PageDomain<Producto> listarTodas(int pagina, int tamanio) {
        Page<ProductoEntity> entityPage = productoJpaRepository
                .findAll(PageRequest.of(pagina, tamanio));

        Page<Producto> page = entityPage.map(mapper::toDomain);

        return new PageDomain<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public PageDomain<Producto> buscarPorTexto(String texto, int pagina, int tamanio){
        Page<ProductoEntity> entityPage = productoJpaRepository
                .findByNombreContainingIgnoreCase(texto,PageRequest.of(pagina, tamanio));

        Page<Producto> page = entityPage.map(mapper::toDomain);
        return new PageDomain<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );


    }

    @Override
    public void eliminar(Long id){
        productoJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return productoJpaRepository.existsByNombre(nombre);
    }

    @Override
    public boolean existePorNombreExcluyendoId(String nombre, Long id) {
        return productoJpaRepository.existsByNombreIgnoreCaseAndIdProductoNot(nombre, id);
    }
}