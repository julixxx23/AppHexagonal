package hexagonal.developer.detallepedido.aplication;

import hexagonal.developer.detallepedido.domain.exception.DetallePedidoNotFoundException;
import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.domain.port.in.BuscarDetallePorIdPort;
import hexagonal.developer.detallepedido.domain.port.out.DetalleRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarDetallePorIdUseCase implements BuscarDetallePorIdPort {
    private final DetalleRepositoryPort detalleRepositoryPort;

    @Override
    public DetallePedido buscarPorId(Long id){
        return detalleRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new DetallePedidoNotFoundException(id));
    }
}
