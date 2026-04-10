package hexagonal.developer.producto.application.usecase;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuardarProductoUseCaseTest {


    @Mock
    private ProductoRepositoryPort productoRepositoryPort;

    @InjectMocks
    private GuardarProductoUseCase guardarProductoUseCase;

    @Test
    @DisplayName("Debe guardar el producto cuando no existe uno con el mismo nombre")
    void debeGuardarProductoCuandoNoExiste() {
        // Arrange
        Producto producto = ProductoFixture.unProductoSinId();
        Producto productoGuardado = ProductoFixture.unProductoValido();

        when(productoRepositoryPort.existePorNombre(producto.getNombre())).thenReturn(false);
        when(productoRepositoryPort.guardar(producto)).thenReturn(productoGuardado);

        // Act
        Producto resultado = guardarProductoUseCase.guardar(producto);

        // Assert
        assertNotNull(resultado);
        assertEquals(productoGuardado.getIdProducto(), resultado.getIdProducto());
        assertEquals(productoGuardado.getNombre(), resultado.getNombre());

        verify(productoRepositoryPort, times(1)).existePorNombre(producto.getNombre());
        verify(productoRepositoryPort, times(1)).guardar(producto);
    }

    @Test
    @DisplayName("Debe lanzar ProductoYaExisteException cuando ya existe un producto con ese nombre")
    void debeLanzarExcepcionCuandoProductoYaExiste() {
        // Arrange
        Producto producto = ProductoFixture.unProductoSinId();

        when(productoRepositoryPort.existePorNombre(producto.getNombre())).thenReturn(true);

        // Act & Assert
        ProductoYaExisteException exception = assertThrows(
                ProductoYaExisteException.class,
                () -> guardarProductoUseCase.guardar(producto)
        );

        assertEquals("Ya existe un producto con ese nombre", exception.getMessage());

        verify(productoRepositoryPort, times(1)).existePorNombre(producto.getNombre());

    }
}