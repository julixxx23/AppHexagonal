package hexagonal.developer.categoria.application;

import hexagonal.developer.categoria.application.usecase.GuardarCategoriaUseCase;
import hexagonal.developer.categoria.domain.exception.CategoriaNotFoundException;
import hexagonal.developer.categoria.domain.exception.CategoriaYaExisteException;
import hexagonal.developer.categoria.domain.model.Categoria;
import hexagonal.developer.categoria.domain.port.out.CategoriaRepositoryPort;
import hexagonal.developer.shared.fixture.CategoriaFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuardarCategoriaUseCaseTest {

    @Mock
    private CategoriaRepositoryPort categoriaRepositoryPort;
    @InjectMocks
    private GuardarCategoriaUseCase categoriaUseCase;

    @Test
    @DisplayName("Deberia de guardar correctamente")
    void guardarCorrecto(){

        Categoria categoria = CategoriaFixture.unaCategoriaSinId();
        Categoria categoriaNueva = CategoriaFixture.unaCategoriaValida();

        when(categoriaRepositoryPort.existePorNombre(categoria.getNombre())).thenReturn(false);
        when(categoriaRepositoryPort.guardar(any(Categoria.class))).thenReturn(categoria);

        Categoria resultado = categoriaUseCase.guardar(categoria);

        assertNotNull(resultado);
        assertEquals(categoria.getIdCategoria(), resultado.getIdCategoria());
        assertEquals(categoria.getNombre(), resultado.getNombre());

        verify(categoriaRepositoryPort, times(1)).existePorNombre(categoria.getNombre());
        verify(categoriaRepositoryPort, times(1)).guardar(any(Categoria.class));
    }
    @Test
    @DisplayName("Deberia de lanzar una exception al guardar")
    void guardarMalo(){

        Categoria categoria = CategoriaFixture.unaCategoriaSinId();

        when(categoriaRepositoryPort.existePorNombre(categoria.getNombre())).thenReturn(true);

        CategoriaYaExisteException exception
                =  assertThrows(CategoriaYaExisteException.class, () ->
                categoriaUseCase.guardar(categoria));

        assertEquals("Ya existe una categoría con ese nombre", exception.getMessage());
        verify(categoriaRepositoryPort, times(1)).existePorNombre(categoria.getNombre());
    }



}
