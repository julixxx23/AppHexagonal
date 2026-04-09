package hexagonal.developer.detallepedido.aplication;

import hexagonal.developer.detallepedido.domain.exception.DetallePedidoNotFoundException;
import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.domain.port.in.ActualizarDetallePort;
import hexagonal.developer.detallepedido.domain.port.out.DetalleRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActualizarDetalleUseCase implements ActualizarDetallePort {
    private final DetalleRepositoryPort detalleRepositoryPort;

    @Override
    public DetallePedido actualizar(Long id, DetallePedido detallePedido){
        DetallePedido validarGuardado = detalleRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new DetallePedidoNotFoundException(id));
        return detalleRepositoryPort.actualizar(id, detallePedido);


    }


}
