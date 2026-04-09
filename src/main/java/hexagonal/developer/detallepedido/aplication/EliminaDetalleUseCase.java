package hexagonal.developer.detallepedido.aplication;

import hexagonal.developer.detallepedido.domain.exception.DetallePedidoNotFoundException;
import hexagonal.developer.detallepedido.domain.port.in.EliminarDetallePort;
import hexagonal.developer.detallepedido.domain.port.out.DetalleRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EliminaDetalleUseCase implements EliminarDetallePort {
    private final DetalleRepositoryPort detalleRepositoryPort;

    @Override
    public void eliminar(Long id){
        detalleRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new DetallePedidoNotFoundException(id));
        detalleRepositoryPort.eliminar(id);
    }

}
