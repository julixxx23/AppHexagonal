package hexagonal.developer.pedido.aplication;

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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CancelarPedidoUseCaseTest {

    @Mock
    private PedidoRepositoryPort pedidoRepositoryPort;
    @InjectMocks
    private CancelarPedidoUseCase cancelarPedidoUseCase;

    @Test
    @DisplayName("Debe de cancelar correctamente")
    void cancelarCorrecto(){

        Pedido pendiente = PedidoFixture.unPedidoPendiente();

        when(pedidoRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(pendiente));
        when(pedidoRepositoryPort.cancelar(any(Pedido.class))).thenReturn(pendiente);

        Pedido resultado = cancelarPedidoUseCase.cancelar(1L);

        assertNotNull(resultado);
        assertEquals(pendiente.getIdPedido(), resultado.getIdPedido());

        verify(pedidoRepositoryPort, times(1)).buscarPorId(1L);
        verify(pedidoRepositoryPort, times(1)).cancelar(any(Pedido.class));
    }
    @Test
    @DisplayName("Debe de lanzar una exception")
    void cancelarMalo(){

        when(pedidoRepositoryPort.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows( PedidoNotFoundException.class, () -> cancelarPedidoUseCase.cancelar(1L));

        verify(pedidoRepositoryPort, never()).cancelar(any());
    }



}
