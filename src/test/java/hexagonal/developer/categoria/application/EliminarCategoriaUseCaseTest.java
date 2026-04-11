package hexagonal.developer.categoria.application;

import hexagonal.developer.categoria.application.usecase.EliminarCategoriaUseCase;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EliminarCategoriaUseCaseTest {

    @Mock
    private CategoriaRepositoryPort categoriaRepositoryPort;
    @InjectMocks
    private EliminarCategoriaUseCase categoriaUseCase;

    @Test
    @DisplayName("Deberia de eliminar una categoria correctamente")
    void eliminarCorrecto(){

        Categoria categoria = CategoriaFixture.unaCategoriaValida();
        Categoria categoriaId = CategoriaFixture.unaCategoriaSinId();

        when(categoriaRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(categoria));

        categoriaUseCase.eliminar(1L);
        verify(categoriaRepositoryPort, times(1)).eliminar(1L);

    }

    @Test
    @DisplayName("Deberia de lanzar una exception al eliminar")
    void eliminarFallo(){

        when(categoriaRepositoryPort.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(CategoriaNotFoundException.class, () ->
                categoriaUseCase.eliminar(1L));

        verify(categoriaRepositoryPort, times(1)).buscarPorId(1L);
        verify(categoriaRepositoryPort, never()).eliminar(1L);
    }
}

