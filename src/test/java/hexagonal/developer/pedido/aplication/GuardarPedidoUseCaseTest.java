package hexagonal.developer.pedido.aplication;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import hexagonal.developer.shared.fixture.PedidoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuardarPedidoUseCaseTest {

    @Mock
    private PedidoRepositoryPort pedidoRepositoryPort;
    @InjectMocks
    private GuardarPedidoUseCase guardarPedidoUseCase;

    @Test
    @DisplayName("Debe guardar un pedido correctamente")
    void guardarCorrecto(){

        Pedido entrada = PedidoFixture.unPedidoSinId();
        Pedido guarda = PedidoFixture.unPedidoValido();

        when(pedidoRepositoryPort.guardar(any(Pedido.class))).thenReturn(entrada);

        Pedido resultado = guardarPedidoUseCase.guardar(entrada);

        assertNotNull(resultado);
        assertEquals(entrada.getIdPedido(), resultado.getIdPedido());

        verify(pedidoRepositoryPort, times(1)).guardar(any(Pedido.class));
    }
}
