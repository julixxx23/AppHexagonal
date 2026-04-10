package hexagonal.developer.producto.application.usecase;

import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.shared.fixture.ProductoFixture;
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
public class ListarProductoUseCaseTest {

    @Mock
    private ProductoRepositoryPort productoRepositoryPort;
    @InjectMocks
    private ListarProductoUseCase listarProductoUseCase;

    @Test
    @DisplayName("Deberia de listar correctamente un producto")
    void buscarCorrecto(){

        int pagina = 0;
        int tamanio = 10;
        PageDomain<Producto> pageLleno = ProductoFixture.unPageDomainDeProductos();

        when(productoRepositoryPort.listarTodas(pagina, tamanio)).thenReturn(pageLleno);

        PageDomain<Producto> resultado = listarProductoUseCase.listarTodas(pagina, tamanio);

        assertEquals(2, resultado.contenido().size());
        assertEquals(0, resultado.paginaActual());
        assertEquals(1, resultado.totalPaginas());
        assertEquals(2L, resultado.totalElementos());

        verify(productoRepositoryPort, times(1)).listarTodas(pagina, tamanio);
    }
    @Test
    @DisplayName("Deberia de lanzar una excepcion al listar")
    void listarMalo(){

        int pagina = 0;
        int tamanio = 10;
        PageDomain<Producto> pageVacio = new PageDomain<>(List.of(), 0,0,0L);

        when(productoRepositoryPort.listarTodas(pagina, tamanio)).thenReturn(pageVacio);

        PageDomain<Producto> resultado = listarProductoUseCase.listarTodas(pagina, tamanio);

        assertNotNull(resultado);
        assertTrue(resultado.contenido().isEmpty());
        assertEquals(0L, resultado.totalElementos());

        verify(productoRepositoryPort, times(1)).listarTodas(pagina, tamanio);
    }


}
