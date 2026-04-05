package hexagonal.developer.usuario.infrastructure.config;

import hexagonal.developer.usuario.aplication.*;
import hexagonal.developer.usuario.domain.port.in.*;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.UsuarioJpaAdapter;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.mapper.UsuarioPersistenceMapper;
import hexagonal.developer.usuario.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsuarioBeanConfig {

    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(
            UsuarioJpaRepository usuarioJpaRepository,
            UsuarioPersistenceMapper mapper){
        return new UsuarioJpaAdapter(usuarioJpaRepository, mapper);
    }

    @Bean
    public GuardarUsuarioPort guardarUsuarioPort(
            UsuarioRepositoryPort usuarioRepositoryPort, PasswordEncoder passwordEncoder){
        return new GuardarUsuarioUseCase(usuarioRepositoryPort, passwordEncoder);
    }

    @Bean
    public ActualizarUsuarioPort actualizarUsuarioPort(UsuarioRepositoryPort usuarioRepositoryPort){
        return new ActualizarUsuarioUseCase(usuarioRepositoryPort);
    }

    @Bean
    public BuscarUsuarioPorIdPort buscarUsuarioPorIdPort(UsuarioRepositoryPort usuarioRepositoryPort){
        return new BuscarUsuarioPorIdUseCase(usuarioRepositoryPort);
    }

    @Bean
    public BuscarUsuarioPorTextoPort buscarUsuarioPorTextoPort(UsuarioRepositoryPort usuarioRepositoryPort){
        return new BuscarUsuarioPorTextoUseCase(usuarioRepositoryPort);
    }

    @Bean
    public ListarTodosUsuariosPort listarTodosUsuariosPort(UsuarioRepositoryPort usuarioRepositoryPort){
        return new ListarTodosUsuariosUseCase(usuarioRepositoryPort);
    }

    @Bean
    public EliminarIUsuarioPort eliminarIUsuarioPort(UsuarioRepositoryPort usuarioRepositoryPort){
        return new EliminarUsuarioUseCase(usuarioRepositoryPort);
    }
}