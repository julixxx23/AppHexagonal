package hexagonal.developer.usuario.applicationusecase;


import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.shared.fixture.UsuarioFixture;
import hexagonal.developer.usuario.aplication.BuscarUsuarioPorTextoUseCase;
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
public class BuscarUsuarioPorTextoUseCaseTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;
    @InjectMocks
    private BuscarUsuarioPorTextoUseCase buscarUsuarioPorTextoUseCase;

    @Test
    @DisplayName("Deberia de buscar un usuario correctamente")
    void buscarCorrecto() {

        String texto = "Julian";
        int pagina = 0;
        int tamanio = 10;
        PageDomain<Usuario> pageEsperado = UsuarioFixture.unPageDomainUsuarios();

        when(usuarioRepositoryPort.buscarPorTexto(texto, pagina, tamanio)).thenReturn(pageEsperado);

        //ACT
        PageDomain<Usuario> resultado = buscarUsuarioPorTextoUseCase.buscarPorTexto(texto, pagina, tamanio);

        assertNotNull(resultado);
        assertEquals(2, resultado.contenido().size());
        assertEquals(0, resultado.paginaActual());
        assertEquals(1, resultado.totalPaginas());
        assertEquals(2L, resultado.totalElementos());

        verify(usuarioRepositoryPort, times(1)).buscarPorTexto(texto, pagina, tamanio);

    }

    @Test
    @DisplayName("Deberia de lanzar una exception al buscar un usuario no registrado")
    void buscarFallido(){
        String texto = "Pedro Escamoso";
        int pagina = 0;
        int tamanio = 0;
        PageDomain<Usuario> pageVacio = new PageDomain<>(List.of(), 0, 0, 0L);

        when(usuarioRepositoryPort.buscarPorTexto(texto, pagina, tamanio)).thenReturn(pageVacio);

        //ACT
        PageDomain<Usuario> resultado = buscarUsuarioPorTextoUseCase.buscarPorTexto(texto, pagina, tamanio);

        assertNotNull(resultado);
        assertTrue(resultado.contenido().isEmpty());
        assertEquals(0L, resultado.totalElementos());

        verify(usuarioRepositoryPort, times(1)).buscarPorTexto(texto, pagina, tamanio);
    }
}
