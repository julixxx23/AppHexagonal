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
class BuscarPorTextoProductoUseCaseTest {

    @Mock
    private ProductoRepositoryPort productoRepositoryPort;

    @InjectMocks
    private BuscarPorTextoProductoUseCase buscarPorTextoProductoUseCase;

    @Test
    @DisplayName("Debe retornar productos que coincidan con el texto buscado")
    void debeRetornarProductosCuandoExisteCoincidencia() {
        // Arrange
        String texto = "Paracetamol";
        int pagina = 0;
        int tamanio = 10;
        PageDomain<Producto> pageEsperado = ProductoFixture.unPageDomainDeProductos();

        when(productoRepositoryPort.buscarPorTexto(texto, pagina, tamanio))
                .thenReturn(pageEsperado);

        // Act
        PageDomain<Producto> resultado = buscarPorTextoProductoUseCase
                .buscarPorTexto(texto, pagina, tamanio);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.contenido().size());
        assertEquals(0, resultado.paginaActual());
        assertEquals(1, resultado.totalPaginas());
        assertEquals(2L, resultado.totalElementos());

        verify(productoRepositoryPort, times(1)).buscarPorTexto(texto, pagina, tamanio);
    }

    @Test
    @DisplayName("Debe retornar página vacía cuando no hay coincidencias")
    void debeRetornarPaginaVaciaCuandoNoHayCoincidencias() {
        // Arrange
        String texto = "xyznoexiste";
        int pagina = 0;
        int tamanio = 10;
        PageDomain<Producto> pageVacio = new PageDomain<>(List.of(), 0, 0, 0L);

        when(productoRepositoryPort.buscarPorTexto(texto, pagina, tamanio))
                .thenReturn(pageVacio);

        // Act
        PageDomain<Producto> resultado = buscarPorTextoProductoUseCase
                .buscarPorTexto(texto, pagina, tamanio);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.contenido().isEmpty());
        assertEquals(0L, resultado.totalElementos());

        verify(productoRepositoryPort, times(1)).buscarPorTexto(texto, pagina, tamanio);
    }
}