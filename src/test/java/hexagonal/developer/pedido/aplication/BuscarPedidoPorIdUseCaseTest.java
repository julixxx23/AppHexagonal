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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarPedidoPorIdUseCaseTest {

    @Mock
    private PedidoRepositoryPort pedidoRepositoryPort;
    @InjectMocks
    private BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;

    @Test
    @DisplayName("Debe buscar correctamente")
    void buscarCorrecto(){

        Pedido entrada = PedidoFixture.unPedidoSinId();

        when(pedidoRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(entrada));

        Pedido resultado = buscarPedidoPorIdUseCase.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(entrada.getIdPedido(), resultado.getIdPedido());

        verify(pedidoRepositoryPort, times(1)).buscarPorId(1L);
    }

    @Test
    @DisplayName("Debe lanzar una exception al buscar")
    void buscarMalo(){


        when(pedidoRepositoryPort.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(PedidoNotFoundException.class, () -> buscarPedidoPorIdUseCase.buscarPorId(1L));

        verify(pedidoRepositoryPort,times(1)).buscarPorId(1L);


    }
}
