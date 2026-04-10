package hexagonal.developer.usuario.applicationusecase;

import hexagonal.developer.shared.fixture.UsuarioFixture;
import hexagonal.developer.usuario.aplication.EstadoUsuarioUseCase;
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
class EstadoUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @InjectMocks
    private EstadoUsuarioUseCase estadoUsuarioUseCase;

    @Test
    @DisplayName("Debe cambiar el estado del usuario cuando existe")
    void debeCambiarEstadoCuandoUsuarioExiste() {
        // Arrange
        Usuario usuario = UsuarioFixture.unUsuarioValido();
        Usuario usuarioInactivo = UsuarioFixture.unUsuarioInactivo();

        when(usuarioRepositoryPort.buscarPorId(1L))
                .thenReturn(Optional.of(usuario));
        when(usuarioRepositoryPort.estado(1L, false))
                .thenReturn(usuarioInactivo);

        // Act
        Usuario resultado = estadoUsuarioUseCase.estado(1L, false);

        // Assert
        assertNotNull(resultado);
        assertEquals(false, resultado.getActivo());

        verify(usuarioRepositoryPort, times(1)).buscarPorId(1L);
        verify(usuarioRepositoryPort, times(1)).estado(1L, false);
    }

    @Test
    @DisplayName("Debe lanzar UsuarioNotFoundException cuando el usuario no existe")
    void debeLanzarExcepcionCuandoUsuarioNoExiste() {
        // Arrange
        when(usuarioRepositoryPort.buscarPorId(99L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                UsuarioNotFoundException.class,
                () -> estadoUsuarioUseCase.estado(99L, false)
        );

        verify(usuarioRepositoryPort, times(1)).buscarPorId(99L);
        verify(usuarioRepositoryPort, never()).estado(any(), any());
    }
}