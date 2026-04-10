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
class EliminarProductoUseCaseTest {

    @Mock
    private ProductoRepositoryPort productoRepositoryPort;

    @InjectMocks
    private EliminarProductoUseCase eliminarProductoUseCase;

    @Test
    @DisplayName("Debe eliminar el producto cuando existe")
    void debeEliminarProductoCuandoExiste() {
        // Arrange
        Producto producto = ProductoFixture.unProductoValido();

        when(productoRepositoryPort.buscarPorId(1L))
                .thenReturn(Optional.of(producto));

        // Act
        eliminarProductoUseCase.eliminar(1L);

        // Assert
        verify(productoRepositoryPort, times(1)).buscarPorId(1L);
        verify(productoRepositoryPort, times(1)).eliminar(1L);
    }

    @Test
    @DisplayName("Debe lanzar ProductoNotFoundException cuando el producto no existe")
    void debeLanzarExcepcionCuandoProductoNoExiste() {
        // Arrange
        when(productoRepositoryPort.buscarPorId(99L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ProductoNotFoundException.class,
                () -> eliminarProductoUseCase.eliminar(99L)
        );

        verify(productoRepositoryPort, times(1)).buscarPorId(99L);
        verify(productoRepositoryPort, never()).eliminar(any());
    }
}