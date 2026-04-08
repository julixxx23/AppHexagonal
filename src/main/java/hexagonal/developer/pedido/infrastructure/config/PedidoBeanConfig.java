package hexagonal.developer.pedido.infrastructure.config;

import hexagonal.developer.pedido.aplication.*;
import hexagonal.developer.pedido.dominio.port.in.*;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import hexagonal.developer.pedido.infrastructure.adapter.out.persistence.PedidoJpaAdapter;
import hexagonal.developer.pedido.infrastructure.adapter.out.persistence.mapper.PedidoPersistenceMapper;
import hexagonal.developer.pedido.infrastructure.adapter.out.persistence.repository.PedidoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfig {

    @Bean
    public PedidoRepositoryPort pedidoRepositoryPort(
            PedidoJpaRepository pedidoJpaRepository,
            PedidoPersistenceMapper mapper){
        return new PedidoJpaAdapter(pedidoJpaRepository, mapper);
    }

    @Bean
    public GuardarPedidoPort guardarPedidoPort(PedidoRepositoryPort pedidoRepositoryPort){
        return new GuardarPedidoUseCase(pedidoRepositoryPort);
    }

    @Bean
    public ActualizarPedidoPort actualizarPedidoPort(PedidoRepositoryPort pedidoRepositoryPort){
        return new ActualizarPedidoUseCase(pedidoRepositoryPort);
    }

    @Bean
    public BuscarPedidoPorIdPort buscarPedidoPorIdPort(PedidoRepositoryPort pedidoRepositoryPort){
        return new BuscarPedidoPorIdUseCase(pedidoRepositoryPort);
    }
    @Bean
    public BuscarPedidoPorTextoPort buscarPedidoPorTextoPort(PedidoRepositoryPort pedidoRepositoryPort){
        return new BuscarPedidoPorTextoUseCase(pedidoRepositoryPort);
    }

    @Bean
    public ListarPedidoPort listarPedidoPort(PedidoRepositoryPort pedidoRepositoryPort){
        return new ListarPedidoUseCase(pedidoRepositoryPort);
    }
    @Bean
    AvanzarEstadoPedidoPort avanzarEstadoPedidoPort(PedidoRepositoryPort pedidoRepositoryPort){
        return new AvanzarEstadoPedidoUseCase(pedidoRepositoryPort);
    }
    @Bean
    public CancelarPedidoPort cancelarPedidoPort(PedidoRepositoryPort pedidoRepositoryPort){
        return new CancelarPedidoUseCase(pedidoRepositoryPort);
    }
}
