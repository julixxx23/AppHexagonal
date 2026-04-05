package hexagonal.developer.categoria.infrastructure.config;

import hexagonal.developer.categoria.application.usecase.*;
import hexagonal.developer.categoria.domain.port.in.*;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.CategoriaJpaAdapter;
import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.mapper.CategoriaPersistenceMapper;
import hexagonal.developer.categoria.infrastructure.adapter.out.persistence.repository.CategoriaJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CategoriaBeanConfig {

    @Bean
    public CategoriaRepositoryPort categoriaRepositoryPort(
            CategoriaJpaRepository jpaRepository,
            CategoriaPersistenceMapper mapper) {
        return new CategoriaJpaAdapter(jpaRepository, mapper);
    }


    @Bean
    public GuardarCategoriaPort guardarCategoriaPort(CategoriaRepositoryPort repositoryPort) {
        return new GuardarCategoriaUseCase(repositoryPort);
    }

    @Bean
    public BuscarPorIdCategoriaPort buscarCategoriaPort(CategoriaRepositoryPort repositoryPort) {
        return new BuscarPorIdCategoriaUseCase(repositoryPort);
    }

    @Bean
    public ListarCategoriasPort listarCategoriasPort(CategoriaRepositoryPort repositoryPort) {
        return new ListarCategoriasUseCase(repositoryPort);
    }

    @Bean
    BuscarPorTextoCategoriaPort buscarPorTextoCategoriaPort(CategoriaRepositoryPort categoriaRepositoryPort){
        return new BuscarPorTextoCategoriaUseCase(categoriaRepositoryPort);
    }

    @Bean
    ActualizarCategoriaPort actualizarCategoriaPort(CategoriaRepositoryPort categoriaRepositoryPort){
        return new ActualizarCategoriaUseCase(categoriaRepositoryPort);
    }

    @Bean
    EliminarCategoriaPort eliminarCategoriaPort(CategoriaRepositoryPort categoriaRepositoryPort){
        return new EliminarCategoriaUseCase(categoriaRepositoryPort);
    }
}