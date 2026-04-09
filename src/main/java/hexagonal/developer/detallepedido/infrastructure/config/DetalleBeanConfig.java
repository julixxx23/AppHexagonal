package hexagonal.developer.detallepedido.infrastructure.config;

import hexagonal.developer.detallepedido.aplication.*;
import hexagonal.developer.detallepedido.domain.port.in.*;
import hexagonal.developer.detallepedido.domain.port.out.DetalleRepositoryPort;
import hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.DetalleJpaAdapter;
import hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.mapper.DetallePersistenceMapper;
import hexagonal.developer.detallepedido.infrastructure.adapter.out.persistence.repository.DetalleJpaRepository;
import hexagonal.developer.pedido.aplication.GuardarPedidoUseCase;
import hexagonal.developer.pedido.dominio.port.in.GuardarPedidoPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DetalleBeanConfig {

    @Bean
    public DetalleRepositoryPort detalleRepositoryPort(
            DetalleJpaRepository detalleJpaRepository,
            DetallePersistenceMapper mapper){
        return new DetalleJpaAdapter(detalleJpaRepository, mapper);
    }

    @Bean
    public GuardarDetallePort guardarDetallePort(DetalleRepositoryPort detalleRepositoryPort){
        return new GuardarDetalleUseCase(detalleRepositoryPort);
    }
    @Bean
    public ActualizarDetallePort actualizarDetallePort(DetalleRepositoryPort detalleRepositoryPort){
        return new ActualizarDetalleUseCase(detalleRepositoryPort);
    }
    @Bean
    public BuscarDetallePorIdPort buscarDetallePorIdPort(DetalleRepositoryPort detalleRepositoryPort){
        return new BuscarDetallePorIdUseCase(detalleRepositoryPort);
    }
    @Bean
    public ListarDetallesPort listarDetallesPort(DetalleRepositoryPort detalleRepositoryPort){
        return new ListarDetalleUseCase(detalleRepositoryPort);
    }
    @Bean
    public EliminarDetallePort eliminarDetallePort(DetalleRepositoryPort detalleRepositoryPort){
        return new EliminaDetalleUseCase(detalleRepositoryPort);
    }


}
