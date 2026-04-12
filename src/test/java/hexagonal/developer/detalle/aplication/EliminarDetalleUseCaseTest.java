package hexagonal.developer.detalle.aplication;

import hexagonal.developer.detallepedido.aplication.EliminaDetalleUseCase;
import hexagonal.developer.detallepedido.domain.exception.DetallePedidoNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EliminarDetalleUseCaseTest {

    @Mock
    private DetalleRepositoryPort detalleRepositoryPort;
    @InjectMocks
    private EliminaDetalleUseCase detalleUseCase;

    @Test
    @DisplayName("Debe de eliminar correctamente")
    void eliminadoCorrecto(){

        DetallePedido pedido = DetalleFixture.unDetalleSinId();
        when(detalleRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(pedido));

        detalleUseCase.eliminar(1L);

        verify(detalleRepositoryPort, times(1)).buscarPorId(1L);
        verify(detalleRepositoryPort, times(1)).eliminar(1L);

    }

    @Test
    @DisplayName("Debe lanzar una exception al elimimar por id no registrado")
    void eliminadoError(){

        when(detalleRepositoryPort.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(DetallePedidoNotFoundException.class, () -> detalleUseCase.eliminar(1L));

        verify(detalleRepositoryPort, times(1)).buscarPorId(1L);

    }
}
