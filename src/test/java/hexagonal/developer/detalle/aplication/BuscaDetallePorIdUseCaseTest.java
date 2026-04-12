package hexagonal.developer.detalle.aplication;

import hexagonal.developer.detallepedido.aplication.BuscarDetallePorIdUseCase;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscaDetallePorIdUseCaseTest {

    @Mock
    private DetalleRepositoryPort detalleRepositoryPort;
    @InjectMocks
    private BuscarDetallePorIdUseCase buscarDetallePorIdUseCase;

    @Test
    @DisplayName("Debe de buscar por Id correctamente")
    void buscarCorrecto(){

        DetallePedido pedido = DetalleFixture.unDetalleSinId();

        when(detalleRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(pedido));
        DetallePedido resultado = buscarDetallePorIdUseCase.buscarPorId(1L);

        assertNotNull(resultado);
        verify(detalleRepositoryPort, times(1)).buscarPorId(1L);
    }

    @Test
    @DisplayName("Debe lanzar una exception al buscar un id no registrado")
    void buscarError(){

        when(detalleRepositoryPort.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(
                DetallePedidoNotFoundException.class, () -> buscarDetallePorIdUseCase.buscarPorId(1L));

        verify(detalleRepositoryPort, times(1)).buscarPorId(1L);
    }
}
