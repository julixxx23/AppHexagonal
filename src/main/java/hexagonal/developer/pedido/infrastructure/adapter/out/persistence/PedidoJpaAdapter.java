package hexagonal.developer.pedido.infrastructure.adapter.out.persistence;

import hexagonal.developer.pedido.dominio.model.EstadoPedido;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import hexagonal.developer.pedido.infrastructure.adapter.out.persistence.entity.PedidoEntity;
import hexagonal.developer.pedido.infrastructure.adapter.out.persistence.mapper.PedidoPersistenceMapper;
import hexagonal.developer.pedido.infrastructure.adapter.out.persistence.repository.PedidoJpaRepository;
import hexagonal.developer.shared.domain.model.PageDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@RequiredArgsConstructor
public class PedidoJpaAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final PedidoPersistenceMapper mapper;

    @Override
    public Pedido guardar(Pedido pedido){
        return mapper.toDomain(pedidoJpaRepository.save(mapper.toEntity(pedido)));
    }

    @Override
    public Pedido actualizar(Long id, Pedido pedido){
        return mapper.toDomain(pedidoJpaRepository.save(mapper.toEntity(pedido)));
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id){
        return pedidoJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Pedido avanzarEstado(Pedido pedido){
        return mapper.toDomain(pedidoJpaRepository.save(mapper.toEntity(pedido)));

    }

    @Override
    public Pedido cancelar(Pedido pedido){
        return mapper.toDomain(pedidoJpaRepository.save(mapper.toEntity(pedido)));
    }

    @Override
    public PageDomain<Pedido> buscarPorTexto(String texto, int pagina, int tamanio){
        Page<PedidoEntity> entityPage = pedidoJpaRepository
                .findByEstadoPedidoContainingIgnoreCase(texto, PageRequest.of(pagina, tamanio));

        Page<Pedido> page = entityPage.map(mapper::toDomain);
        return new PageDomain<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public PageDomain<Pedido> listar(int pagina, int tamanio){
        Page<PedidoEntity> entityPage = pedidoJpaRepository
                .findAll(PageRequest.of(pagina, tamanio));

        Page<Pedido> page = entityPage.map(mapper::toDomain);
        return new PageDomain<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public boolean existeYEstaActivo(Pedido pedido){
        return pedidoJpaRepository.existsByIdPedidoAndActivoTrue(pedido.getIdPedido());
    }
}
