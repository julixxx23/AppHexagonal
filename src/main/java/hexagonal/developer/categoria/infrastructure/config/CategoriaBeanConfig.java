package hexagonal.developer.categoria.infrastructure.config;

import hexagonal.developer.categoria.application.usecase.BuscarCategoriaUseCase;
import hexagonal.developer.categoria.application.usecase.GuardarCategoriaUseCase;
import hexagonal.developer.categoria.application.usecase.ListarCategoriasUseCase;
import hexagonal.developer.categoria.domain.port.in.BuscarCategoriaPort;
import hexagonal.developer.categoria.domain.port.in.GuardarCategoriaPort;
import hexagonal.developer.categoria.domain.port.in.ListarCategoriasPort;
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
    public BuscarCategoriaPort buscarCategoriaPort(CategoriaRepositoryPort repositoryPort) {
        return new BuscarCategoriaUseCase(repositoryPort);
    }

    @Bean
    public ListarCategoriasPort listarCategoriasPort(CategoriaRepositoryPort repositoryPort) {
        return new ListarCategoriasUseCase(repositoryPort);
    }
}