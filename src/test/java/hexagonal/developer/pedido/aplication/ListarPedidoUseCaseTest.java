package hexagonal.developer.pedido.aplication;

import hexagonal.developer.pedido.dominio.model.Pedido;
import hexagonal.developer.pedido.dominio.port.out.PedidoRepositoryPort;
import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.shared.fixture.PedidoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListarPedidoUseCaseTest {

    @Mock
    private PedidoRepositoryPort pedidoRepositoryPort;
    @InjectMocks
    private ListarPedidoUseCase listarPedidoUseCase;

    @Test
    @DisplayName("Debe listar correcto")
    void listarCorrecto(){

        Pedido entrada = PedidoFixture.unPedidoSinId();

        int pagina = 0;
        int tamanio = 10;
        PageDomain<Pedido> pageCorrecto = PedidoFixture.unPageDomainDetalles();

        when(pedidoRepositoryPort.listar(pagina, tamanio)).thenReturn(pageCorrecto);

        PageDomain<Pedido> resultado = listarPedidoUseCase.listar(pagina, tamanio);

        assertEquals(2, resultado.contenido().size());
        assertEquals(0, resultado.paginaActual());
        assertEquals(1, resultado.totalPaginas());
        assertEquals(2L, resultado.totalElementos());

        verify(pedidoRepositoryPort, times(1)).listar(pagina, tamanio);
    }

    @Test
    @DisplayName("Debe lanzar una exception al listar")
    void listarFallo(){

        int pagina = 0;
        int tamanio = 10;
        PageDomain<Pedido> pageVacio = new PageDomain<>(List.of(), 0, 0 ,0);

        when(pedidoRepositoryPort.listar(pagina, tamanio)).thenReturn(pageVacio);

        PageDomain<Pedido> resultado = listarPedidoUseCase.listar(pagina, tamanio);

        assertNotNull(resultado);
        assertTrue(resultado.contenido().isEmpty());
        assertEquals(0, resultado.totalElementos());

        verify(pedidoRepositoryPort, times(1)).listar(pagina, tamanio);
    }

}
