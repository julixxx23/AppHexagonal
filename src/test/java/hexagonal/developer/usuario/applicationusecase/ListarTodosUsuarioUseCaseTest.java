package hexagonal.developer.usuario.applicationusecase;

import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.shared.fixture.UsuarioFixture;
import hexagonal.developer.usuario.aplication.ListarTodosUsuariosUseCase;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.out.UsuarioRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListarTodosUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;
    @InjectMocks
    private ListarTodosUsuariosUseCase listarTodosUsuariosUseCase;

    @Test
    @DisplayName("Deberia de listar un usuario correctamente")
    void listarCorrecto(){

        int pagina = 0;
        int tamanio = 10;
        PageDomain<Usuario> pageCorrecto = UsuarioFixture.unPageDomainUsuarios();

        when(usuarioRepositoryPort.listarTodos(pagina, tamanio)).thenReturn(pageCorrecto);

        PageDomain<Usuario> resultado = listarTodosUsuariosUseCase.listarTodos(pagina, tamanio);

        assertEquals(2, resultado.contenido().size());
        assertEquals(0, resultado.paginaActual());
        assertEquals(1, resultado.totalPaginas());
        assertEquals(2L, resultado.totalElementos());

        verify(usuarioRepositoryPort, times(1)).listarTodos(pagina, tamanio);

    }

    @Test
    @DisplayName("deberia de lanzar una excepcion al listar")
    void listarMalo(){

        int pagina = 0;
        int tamanio = 10;
        PageDomain<Usuario> pageVacio = new PageDomain<>(List.of(), 0, 0, 0L);

        when(usuarioRepositoryPort.listarTodos(pagina, tamanio)).thenReturn(pageVacio);

        PageDomain<Usuario> resultado = listarTodosUsuariosUseCase.listarTodos(pagina, tamanio);

        assertNotNull(resultado);
        assertTrue(resultado.contenido().isEmpty());
        assertEquals(0L, resultado.totalElementos());

        verify(usuarioRepositoryPort, times(1)).listarTodos(pagina, tamanio);
    }
}
