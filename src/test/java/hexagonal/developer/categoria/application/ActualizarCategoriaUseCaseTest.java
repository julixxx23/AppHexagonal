package hexagonal.developer.categoria.application;

import hexagonal.developer.categoria.application.usecase.ActualizarCategoriaUseCase;
import hexagonal.developer.categoria.domain.exception.CategoriaNotFoundException;
import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import hexagonal.developer.shared.fixture.CategoriaFixture;
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
public class ActualizarCategoriaUseCaseTest {

    @Mock
    private CategoriaRepositoryPort categoriaRepositoryPort;
    @InjectMocks
    private ActualizarCategoriaUseCase actualizarCategoriaUseCase;

    @Test
    @DisplayName("Deberia de actualizar una categoria corrrectamente")
    void actualizarCorrecto(){

        Categoria entrada = CategoriaFixture.unaCategoriaSinId();
        Categoria actualizada = CategoriaFixture.unaCategoriaValida();

        when(categoriaRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(actualizada));
        when(categoriaRepositoryPort.existePorNombreExcluyendoId(entrada.getNombre(), 1L))
                .thenReturn(false);
        when(categoriaRepositoryPort.actualizar(eq(1L), any(Categoria.class))).thenReturn(actualizada);

        Categoria resultado = actualizarCategoriaUseCase.actualizar(1L, entrada);

        assertNotNull(resultado);
        assertEquals(actualizada.getIdCategoria(), resultado.getIdCategoria());
        assertEquals(actualizada.getNombre(), resultado.getNombre());

        verify(categoriaRepositoryPort, times(1)).buscarPorId(1L);
        verify(categoriaRepositoryPort, times(1)).existePorNombreExcluyendoId(entrada.getNombre(), 1L);
        verify(categoriaRepositoryPort, times(1)).actualizar(eq(1L), any(Categoria.class));
    }

    @Test
    @DisplayName("Debe lanzar CategoriaNotFoundException cuando el id  no existe")
    void buscaId(){

        Categoria entrada = CategoriaFixture.unaCategoriaSinId();

        when(categoriaRepositoryPort.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(
                CategoriaNotFoundException.class, () -> actualizarCategoriaUseCase.actualizar(1L, entrada));

        verify(categoriaRepositoryPort, times(1)).buscarPorId(1L);
        verify(categoriaRepositoryPort, never()).existePorNombreExcluyendoId(any(), any());
        verify(categoriaRepositoryPort, never()).actualizar(any(), any());
    }
}
