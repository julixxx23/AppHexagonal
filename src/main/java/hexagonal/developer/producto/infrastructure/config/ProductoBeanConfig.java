package hexagonal.developer.producto.infrastructure.config;

import hexagonal.developer.producto.application.usecase.*;
import hexagonal.developer.producto.domain.port.in.*;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductoBeanConfig {

    @Bean
    public GuardarProductoPort guardarProductoPort(ProductoRepositoryPort repositoryPort){
        return new GuardarProductoUseCase(repositoryPort);
    }

    @Bean
    public BuscarProductoPort buscarProductoPort(ProductoRepositoryPort productoRepositoryPort){
        return new BuscarProductoUseCase(productoRepositoryPort);
    }

    @Bean
    public ListarProductosPort listarProductosPort(ProductoRepositoryPort productoRepositoryPort){
        return new ListarCategoriaUseCase(productoRepositoryPort);
    }

    @Bean
    public BuscarPorTextoProductoPort buscarPorTextoProductoPort(ProductoRepositoryPort productoRepositoryPort){
        return new BuscarPorTextoProductoUseCase(productoRepositoryPort);
    }
    @Bean
    public ActualizarProductoPort actualizarProductoPort(ProductoRepositoryPort productoRepositoryPort){
        return new ActualizarProductoUseCase(productoRepositoryPort);
    }
    @Bean
    public EliminarProductoPort eliminarProductoPort(ProductoRepositoryPort productoRepositoryPort){
        return new EliminarProductoUseCase(productoRepositoryPort);
    }


}
