package hexagonal.developer.producto.application.usecase;

import hexagonal.developer.producto.domain.exception.ProductoNotFoundException;
import hexagonal.developer.producto.domain.model.Producto;
import hexagonal.developer.producto.domain.port.out.ProductoRepositoryPort;
import hexagonal.developer.shared.fixture.ProductoFixture;
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
class BuscarProductoUseCaseTest {

    @Mock
    private ProductoRepositoryPort productoRepositoryPort;

    @InjectMocks
    private BuscarProductoUseCase buscarProductoUseCase;

    @Test
    @DisplayName("Debe retornar el producto cuando existe")
    void debeRetornarProductoCuandoExiste() {
        // Arrange
        Producto producto = ProductoFixture.unProductoValido();

        when(productoRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(producto));

        // Act
        Producto resultado = buscarProductoUseCase.buscarPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(producto.getIdProducto(), resultado.getIdProducto());
        assertEquals(producto.getNombre(), resultado.getNombre());

        verify(productoRepositoryPort, times(1)).buscarPorId(1L);
    }

    @Test
    @DisplayName("Debe lanzar ProductoNotFoundException cuando no existe")
    void debeLanzarExcepcionCuandoProductoNoExiste() {
        // Arrange
        when(productoRepositoryPort.buscarPorId(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ProductoNotFoundException.class,
                () -> buscarProductoUseCase.buscarPorId(99L)
        );

        verify(productoRepositoryPort, times(1)).buscarPorId(99L);
    }
}