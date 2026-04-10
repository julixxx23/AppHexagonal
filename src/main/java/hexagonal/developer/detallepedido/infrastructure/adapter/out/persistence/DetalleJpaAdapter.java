package hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence;

import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.domain.port.out.DetalleRepositoryPort;
import hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.entity.DetallePedidoEntity;
import hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.mapper.DetallePersistenceMapper;
import hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.repository.DetalleJpaRepository;
import hexagonal.developer.shared.domain.model.PageDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@RequiredArgsConstructor
public class DetalleJpaAdapter implements DetalleRepositoryPort {

    private final DetalleJpaRepository detalleJpaRepository;
    private final DetallePersistenceMapper mapper;

    @Override
    public DetallePedido guardar(DetallePedido detallePedido){
        return mapper.toDomain(detalleJpaRepository.save(mapper.toEntity(detallePedido)));
    }

    @Override
    public DetallePedido actualizar(Long id, DetallePedido detallePedido){
        return mapper.toDomain(detalleJpaRepository.save(mapper.toEntity(detallePedido)));
    }

    @Override
    public Optional<DetallePedido> buscarPorId(Long id){
        return detalleJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public PageDomain<DetallePedido> listar(int pagina, int tamanio){
        Page<DetallePedidoEntity> pageEntity = detalleJpaRepository
                .findAll(PageRequest.of(pagina, tamanio));

        Page<DetallePedido> page = pageEntity.map(mapper::toDomain);
        return new PageDomain<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
    @Override
    public void eliminar(Long id){
        detalleJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorPedidoYProducto(Long idPedido, Long idProducto){
        return detalleJpaRepository.existsByPedidoIdPedidoAndProductoIdProducto(idPedido, idProducto);
    }

}
