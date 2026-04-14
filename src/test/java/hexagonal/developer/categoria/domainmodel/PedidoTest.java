package hexagonal.developer.categoria.domainmodel;

import hexagonal.developer.pedido.dominio.exception.PedidoNoModificableException;
import hexagonal.developer.pedido.dominio.model.EstadoPedido;
import hexagonal.developer.pedido.dominio.model.Pedido;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PedidoTest {

    @Nested
    class AvanzarEstado{
        @Test
        void dePendienteAConfirmado(){

            Pedido pedido = Pedido.builder()
                    .idPedido(1L)
                    .estadoPedido(EstadoPedido.PENDIENTE)
                    .build();

            pedido.avanzarEstado();

            assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstadoPedido());
        }

        @Test
        void deConfirmadoAEnproceso(){

            Pedido pedido = Pedido.builder()
                    .idPedido(1L)
                    .estadoPedido(EstadoPedido.CONFIRMADO)
                    .build();

            pedido.avanzarEstado();

            assertEquals(EstadoPedido.EN_PROCESO, pedido.getEstadoPedido());
        }

        @Test
        void deEnprocesoAEnviado(){

            Pedido pedido = Pedido.builder()
                    .idPedido(1L)
                    .estadoPedido(EstadoPedido.EN_PROCESO)
                    .build();
            pedido.avanzarEstado();

            assertEquals(EstadoPedido.ENVIADO, pedido.getEstadoPedido());
        }

        @Test
        void deEnviadoAEntregado(){

            Pedido pedido = Pedido.builder()
                    .idPedido(1L)
                    .estadoPedido(EstadoPedido.ENVIADO)
                    .build();

            pedido.avanzarEstado();

            assertEquals(EstadoPedido.ENTREGADO, pedido.getEstadoPedido());
        }

        @Test
        void falloException(){

            Pedido pedido = Pedido.builder()
                    .estadoPedido(EstadoPedido.ENTREGADO)
                    .build();

            assertThrows(
                    IllegalStateException.class, () -> pedido.avanzarEstado()
            );
        }

    }

    @Nested
    class Cancelar{
        @Test
        void deEstadoAEntregado(){

            Pedido pedido = Pedido.builder()
                    .idPedido(1L)
                    .estadoPedido(EstadoPedido.PENDIENTE)
                    .build();

            System.out.println(pedido.getEstadoPedido());
            pedido.cancelar();

            assertEquals(EstadoPedido.CANCELADO, pedido.getEstadoPedido());
        }

        @Test
        void falloException(){

            Pedido pedido = Pedido.builder()
                    .estadoPedido(EstadoPedido.ENTREGADO)
                    .build();

            assertThrows(
                    PedidoNoModificableException.class, () -> pedido.cancelar()
            );
        }
    }

    @Nested
    class ValidarEditable{
        @Test
        void deEstadoPedidoAPendiente(){

            Pedido pedido = Pedido.builder()
                    .idPedido(1L)
                    .estadoPedido(EstadoPedido.PENDIENTE)
                    .build();

            pedido.validarEditable();

            assertEquals(EstadoPedido.PENDIENTE, pedido.getEstadoPedido());
        }

        @Test
        void falloException(){
            Pedido pedido = Pedido.builder()
                    .estadoPedido(EstadoPedido.CONFIRMADO)
                    .build();

            assertThrows(
                    PedidoNoModificableException.class, () -> pedido.validarEditable()
            );
        }

    }




}
