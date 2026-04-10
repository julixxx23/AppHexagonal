package hexagonal.developer.usuario.applicationusecase;

import hexagonal.developer.shared.fixture.UsuarioFixture;
import hexagonal.developer.usuario.aplication.BuscarUsuarioPorIdUseCase;
import hexagonal.developer.usuario.domain.exception.UsuarioNotFoundException;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarUsuarioPorIdUseCaseTest {
    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;
    @InjectMocks
    private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    @Test
    @DisplayName("Deberia de buscar un usuarioId correctamente")
    void buscarUsuario(){

        Usuario usuario = UsuarioFixture.unUsuarioValido();

        when(usuarioRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = buscarUsuarioPorIdUseCase.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(usuario.getIdUsuario(), resultado.getIdUsuario());
        assertEquals(usuario.getNombreUsuario(), resultado.getNombreUsuario());

        verify(usuarioRepositoryPort, times(1)).buscarPorId(1L);

    }

    @Test
    @DisplayName("Deberia de lanzar una exception al no encontrar el id")
    void buscarMalo(){

        when(usuarioRepositoryPort.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(
                UsuarioNotFoundException.class, () -> buscarUsuarioPorIdUseCase.buscarPorId(1L));

        verify(usuarioRepositoryPort, times(1)).buscarPorId(1L);
    }
}
