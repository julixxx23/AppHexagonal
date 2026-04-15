package hexagonal.developer.detalle.domain;

import hexagonal.developer.detallepedido.domain.exception.ElDetalleCantidadEsObligatorio;
import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DetalleTest {

    @Nested
    class CalcularTotal{
        @Test
        void debeCalcularElTotal(){

            DetallePedido detallePedido = DetallePedido.builder()
                    .idDetalle(1L)
                    .cantidad(5)
                    .precioUnitario(BigDecimal.valueOf(10.20))
                    .build();

            BigDecimal resultado = detallePedido.calcularSubTotal();

            assertEquals(0, BigDecimal.valueOf(51.00).compareTo(resultado));

        }

        @Test
        void debeLanzarException(){

            DetallePedido detallePedido = DetallePedido.builder()
                    .idDetalle(1L)
                    .precioUnitario(BigDecimal.valueOf(20.20))
                    .build();

            assertThrows( ElDetalleCantidadEsObligatorio.class, () -> detallePedido.calcularSubTotal());


        }


    }
}
