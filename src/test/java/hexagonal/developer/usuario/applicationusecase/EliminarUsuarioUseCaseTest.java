package hexagonal.developer.usuario.applicationusecase;

import hexagonal.developer.shared.fixture.UsuarioFixture;
import hexagonal.developer.usuario.aplication.EliminarUsuarioUseCase;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EliminarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;
    @InjectMocks
    private EliminarUsuarioUseCase eliminarUsuarioUseCase;

    @Test
    @DisplayName("Deberia de eliminar un producto correctamente")
    void eliminarCorrecto(){

        Usuario usuario = UsuarioFixture.unUsuarioValido();

        when(usuarioRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(usuario));

        eliminarUsuarioUseCase.eliminar(1L);

        verify(usuarioRepositoryPort,times(1)).buscarPorId(1L);
        verify(usuarioRepositoryPort, times(1)).eliminar(1L);
    }

    @Test
    @DisplayName("Deberia de lanzar una exception al eliminar un usuario")
    void eliminarFallo(){
        when(usuarioRepositoryPort.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> eliminarUsuarioUseCase.eliminar(99L));

        verify(usuarioRepositoryPort, times(1)).buscarPorId(99L);
        verify(usuarioRepositoryPort, never()).eliminar(any());
    }
}
