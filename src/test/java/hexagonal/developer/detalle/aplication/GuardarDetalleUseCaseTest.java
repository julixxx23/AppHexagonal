package hexagonal.developer.detalle.aplication;

import hexagonal.developer.detallepedido.aplication.GuardarDetalleUseCase;
import hexagonal.developer.detallepedido.domain.exception.DetalleYaExisteException;
import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.domain.port.out.DetalleRepositoryPort;
import hexagonal.developer.shared.fixture.DetalleFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuardarDetalleUseCaseTest {
    @Mock
    private DetalleRepositoryPort detalleRepositoryPort;
    @InjectMocks
    private GuardarDetalleUseCase guardarDetalleUseCase;

    @Test
    @DisplayName("Debe de guardar un detalle correctamente")
    void detalleCorrecto(){

        DetallePedido entrada = DetalleFixture.unDetalleSinId();

        when(detalleRepositoryPort.existePorPedidoYProducto
                (entrada.getPedido().getIdPedido(),
                        entrada.getProducto().getIdProducto())).thenReturn(false);
        when(detalleRepositoryPort.guardar(any(DetallePedido.class))).thenReturn(entrada);

        DetallePedido resultado = guardarDetalleUseCase.guardar(entrada);

        assertNotNull(resultado);
        assertEquals(entrada.getIdDetalle(), resultado.getIdDetalle());

        verify(detalleRepositoryPort, times(1)).existePorPedidoYProducto(entrada.getPedido().getIdPedido(),
                entrada.getProducto().getIdProducto());
        verify(detalleRepositoryPort, times(1)).guardar(entrada);
    }

    @Test
    @DisplayName("Debe de lanzar una exception")
    void detallaFallo(){

        DetallePedido entrada = DetalleFixture.unDetalleSinId();

        when(detalleRepositoryPort.existePorPedidoYProducto(
                entrada.getPedido().getIdPedido(),
                entrada.getProducto().getIdProducto())).thenReturn(true);

        DetalleYaExisteException exception = assertThrows(
                DetalleYaExisteException.class, () -> guardarDetalleUseCase.guardar(entrada));

        assertEquals("El producto ya esta en el pedido", exception.getMessage());
        verify(detalleRepositoryPort, times(1)).existePorPedidoYProducto(entrada.getProducto().getIdProducto(),
                entrada.getPedido().getIdPedido());


    }




}
