package hexagonal.developer.usuario.applicationusecase;


import hexagonal.developer.shared.fixture.UsuarioFixture;
import hexagonal.developer.usuario.aplication.GuardarUsuarioUseCase;
import hexagonal.developer.usuario.domain.exception.UsuarioYaExisteException;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GuardarUsuarioUseCaseTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;
    @InjectMocks
    private GuardarUsuarioUseCase guardarUsuarioUseCase;

    @Test
    @DisplayName("Debe validar la creacion correcta de un usuario")
    void creacionExitosa(){

        Usuario usuario = UsuarioFixture.unUsuarioValido();

        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(usuarioRepositoryPort.existePorUsuarioUsuario(usuario.getUsuarioUsuario())).thenReturn(false);
        when(usuarioRepositoryPort.guardar(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = guardarUsuarioUseCase.guardar(usuario);

        assertNotNull(resultado);
        assertEquals(usuario.getIdUsuario(), resultado.getIdUsuario());
        assertEquals(usuario.getNombreUsuario(), resultado.getNombreUsuario());

        verify(usuarioRepositoryPort, times(1)).existePorUsuarioUsuario(usuario.getUsuarioUsuario());
        verify(usuarioRepositoryPort, times(1)).guardar(any(Usuario.class));
    }

    @Test
    @DisplayName("Deberia lanzar una exception cuando ya existe un usuario con el mismo nombre")
    void lanzarException(){

        Usuario usuario = UsuarioFixture.unUsuarioSinId();

        when(usuarioRepositoryPort.existePorUsuarioUsuario(usuario.getUsuarioUsuario())).thenReturn(true);

        UsuarioYaExisteException exception = assertThrows(
                UsuarioYaExisteException.class, () -> guardarUsuarioUseCase.guardar(usuario));

        assertEquals("El nombre de usuario ya está en uso", exception.getMessage());
        verify(usuarioRepositoryPort,times(1)).existePorUsuarioUsuario(usuario.getUsuarioUsuario());
    }


}
