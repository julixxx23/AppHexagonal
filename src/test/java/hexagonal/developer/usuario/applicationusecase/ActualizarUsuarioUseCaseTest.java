package hexagonal.developer.usuario.applicationusecase;

import hexagonal.developer.shared.fixture.UsuarioFixture;
import hexagonal.developer.usuario.aplication.ActualizarUsuarioUseCase;
import hexagonal.developer.usuario.aplication.EstadoUsuarioUseCase;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActualizarUsuarioUseCaseTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;
    @InjectMocks
    private ActualizarUsuarioUseCase actualizarUsuarioUseCase;

    //Test
    @Test
    @DisplayName("Deberia actualizar correctamente al usuario")
    void actualizacionCorrecta() {

        //Arrange
        Usuario usuario = UsuarioFixture.unUsuarioSinId();
        Usuario usuarioNuevo = UsuarioFixture.unUsuarioValido();

        // action y reaction
        when(usuarioRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(usuarioNuevo));
        when(usuarioRepositoryPort.existePorUsuarioUsuarioExcluyendoId(usuario.getUsuarioUsuario(), 1L))
                .thenReturn(false);
        when(usuarioRepositoryPort.actualizar(1L, usuario)).thenReturn(usuarioNuevo);

        Usuario resultado = actualizarUsuarioUseCase.actualizar(1L, usuario);

        //asrt
        assertNotNull(resultado);
        assertEquals(usuarioNuevo.getIdUsuario(), resultado.getIdUsuario());
        assertEquals(usuarioNuevo.getUsuarioUsuario(), resultado.getUsuarioUsuario());

        // act
        verify(usuarioRepositoryPort, times(1)).buscarPorId(1L);
        verify(usuarioRepositoryPort, times(1)).existePorUsuarioUsuarioExcluyendoId(usuario.getUsuarioUsuario(), 1L);
        verify(usuarioRepositoryPort, times(1)).actualizar(1L, usuario);
    }
}
