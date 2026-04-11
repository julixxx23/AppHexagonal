package hexagonal.developer.categoria.application;

import hexagonal.developer.categoria.application.usecase.ListarCategoriasUseCase;
import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.shared.fixture.CategoriaFixture;
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
public class ListarCategoriaUseCaseTest {

    @Mock
    private CategoriaRepositoryPort categoriaRepositoryPort;
    @InjectMocks
    private ListarCategoriasUseCase listarCategoriasUseCase;

    @Test
    @DisplayName("Deberia de listar una categoria")
    void buscarCorrecto(){

        int pagina = 0;
        int tamanio = 10;
        PageDomain<Categoria> pageCorrecto = CategoriaFixture.unPageDomaninDeCategorias();

        when(categoriaRepositoryPort.listarTodas(pagina, tamanio)).thenReturn(pageCorrecto);

        PageDomain<Categoria> resultado = listarCategoriasUseCase.listarTodas(pagina, tamanio);

        assertNotNull(resultado);
        assertEquals(2, resultado.contenido().size());
        assertEquals(0, resultado.paginaActual());
        assertEquals(1, resultado.totalPaginas());
        assertEquals(2L, resultado.totalElementos());

        verify(categoriaRepositoryPort, times(1)).listarTodas(pagina, tamanio);
    }

    @Test
    @DisplayName("Deberia de lanzar una exception al listar")
    void listarError(){

        int pagina = 0;
        int tamanio = 10;
        PageDomain<Categoria> pageVacio = new PageDomain<>(List.of(), 0, 0, 0L);

        when(categoriaRepositoryPort.listarTodas(pagina, tamanio)).thenReturn(pageVacio);

        PageDomain<Categoria> resultado = listarCategoriasUseCase.listarTodas(pagina, tamanio);

        assertNotNull(resultado);
        assertTrue(resultado.contenido().isEmpty());
        assertEquals(0L, resultado.totalElementos());

        verify(categoriaRepositoryPort, times(1)).listarTodas(pagina, tamanio);
    }

}
