package hexagonal.developer.pedido.aplication;

import hexagonal.developer.categoria.domain.exception.CategoriaNotFoundException;
import hexagonal.developer.pedido.dominio.exception.PedidoNotFoundException;
import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import hexagonal.developer.shared.fixture.PedidoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActualizarPedidoUseCaseTest {

    @Mock
    private PedidoRepositoryPort pedidoRepositoryPort;
    @InjectMocks
    private ActualizarPedidoUseCase actualizarPedidoUseCase;

    @Test
    @DisplayName("Debe actualizar correctamente")
    void editaCorrecto() {

        Pedido entrada = PedidoFixture.unPedidoSinId();
        Pedido actualiza = PedidoFixture.unPedidoPendiente();

        when(pedidoRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(actualiza));
        when(pedidoRepositoryPort.actualizar(eq(1L), any(Pedido.class))).thenReturn(actualiza);

        Pedido resultado = actualizarPedidoUseCase.actualizar(1L, entrada);

        assertNotNull(resultado);
        assertEquals(actualiza.getIdPedido(), resultado.getIdPedido());

        verify(pedidoRepositoryPort, times((1))).buscarPorId(1L);
        verify(pedidoRepositoryPort, times(1)).actualizar(eq(1L), any(Pedido.class));

    }

    @Test
    @DisplayName("Debe de lanzar una exception")
    void actualizaError(){

        Pedido entrada = PedidoFixture.unPedidoSinId();

        when(pedidoRepositoryPort.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(PedidoNotFoundException.class, () -> actualizarPedidoUseCase.actualizar(1L, entrada) );

        verify(pedidoRepositoryPort,times(1)).buscarPorId(1L);
        verify(pedidoRepositoryPort, never()).actualizar(any(),any());
    }



}
