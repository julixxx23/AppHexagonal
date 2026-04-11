package hexagonal.developer.categoria.application;

import hexagonal.developer.categoria.application.usecase.BuscarPorIdCategoriaUseCase;
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
public class BuscarCategoriaPorIdTest {

    @Mock
    private CategoriaRepositoryPort categoriaRepositoryPort;
    @InjectMocks
    private BuscarPorIdCategoriaUseCase buscarPorIdCategoriaUseCase;

    @Test
    @DisplayName("Deberia de buscar por id correctamente")
    void buscarCorrecto(){

        Categoria categoria = CategoriaFixture.unaCategoriaValida();
        Categoria categoriaId = CategoriaFixture.unaCategoriaSinId();

        when(categoriaRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(categoria));
        Categoria resultado = buscarPorIdCategoriaUseCase.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(categoria.getIdCategoria(), resultado.getIdCategoria());
        verify(categoriaRepositoryPort, times(1)).buscarPorId(1L);
    }
    @Test
    @DisplayName("Deberia de lanzar una exception al buscar")
    void buscarMalo(){
         when(categoriaRepositoryPort.buscarPorId(1L)).thenReturn(Optional.empty());

         assertThrows(
                 CategoriaNotFoundException.class, () -> buscarPorIdCategoriaUseCase.buscarPorId(1L));

         verify(categoriaRepositoryPort, times(1)).buscarPorId(1L);
    }




}
