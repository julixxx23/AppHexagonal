package hexagonal.developer.producto.application.usecase;

import hexagonal.developer.producto.domain.exception.ProductoNotFoundException;
import hexagonal.developer.producto.domain.exception.ProductoYaExisteException;
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
class ActualizarProductoUseCaseTest {

    @Mock
    private ProductoRepositoryPort productoRepositoryPort;

    @InjectMocks
    private ActualizarProductoUseCase actualizarProductoUseCase;

    @Test
    @DisplayName("Debe actualizar el producto cuando existe y el nombre no está duplicado")
    void debeActualizarProductoCuandoEsValido() {
        // Arrange
        Producto productoEntrada = ProductoFixture.unProductoSinId();
        Producto productoActualizado = ProductoFixture.unProductoValido();

        when(productoRepositoryPort.buscarPorId(1L))
                .thenReturn(Optional.of(productoActualizado));
        when(productoRepositoryPort.existePorNombreExcluyendoId(productoEntrada.getNombre(), 1L))
                .thenReturn(false);
        when(productoRepositoryPort.actualizar(1L, productoEntrada))
                .thenReturn(productoActualizado);

        // Act
        Producto resultado = actualizarProductoUseCase.actualizar(1L, productoEntrada);

        // Assert
        assertNotNull(resultado);
        assertEquals(productoActualizado.getIdProducto(), resultado.getIdProducto());
        assertEquals(productoActualizado.getNombre(), resultado.getNombre());

        verify(productoRepositoryPort, times(1)).buscarPorId(1L);
        verify(productoRepositoryPort, times(1)).existePorNombreExcluyendoId(productoEntrada.getNombre(), 1L);
        verify(productoRepositoryPort, times(1)).actualizar(1L, productoEntrada);
    }

    @Test
    @DisplayName("Debe lanzar ProductoNotFoundException cuando el id no existe")
    void debeLanzarExcepcionCuandoProductoNoExiste() {
        // Arrange
        Producto productoEntrada = ProductoFixture.unProductoSinId();

        when(productoRepositoryPort.buscarPorId(99L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ProductoNotFoundException.class,
                () -> actualizarProductoUseCase.actualizar(99L, productoEntrada)
        );

        verify(productoRepositoryPort, times(1)).buscarPorId(99L);
        verify(productoRepositoryPort, never()).existePorNombreExcluyendoId(any(), any());
        verify(productoRepositoryPort, never()).actualizar(any(), any());
    }

    @Test
    @DisplayName("Debe lanzar ProductoYaExisteException cuando el nombre ya lo usa otro producto")
    void debeLanzarExcepcionCuandoNombreYaExiste() {
        // Arrange
        Producto productoEntrada = ProductoFixture.unProductoSinId();
        Producto productoExistente = ProductoFixture.unProductoValido();

        when(productoRepositoryPort.buscarPorId(1L))
                .thenReturn(Optional.of(productoExistente));
        when(productoRepositoryPort.existePorNombreExcluyendoId(productoEntrada.getNombre(), 1L))
                .thenReturn(true);

        // Act & Assert
        ProductoYaExisteException exception = assertThrows(
                ProductoYaExisteException.class,
                () -> actualizarProductoUseCase.actualizar(1L, productoEntrada)
        );

        assertEquals("Ya existe un producto con ese nombre", exception.getMessage());

        verify(productoRepositoryPort, times(1)).buscarPorId(1L);
        verify(productoRepositoryPort, times(1)).existePorNombreExcluyendoId(productoEntrada.getNombre(), 1L);
        verify(productoRepositoryPort, never()).actualizar(any(), any());
    }
}