package hexagonal.developer.detalle.aplication;

import hexagonal.developer.detallepedido.aplication.ListarDetalleUseCase;
import hexagonal.developer.detallepedido.domain.model.DetallePedido;
import hexagonal.developer.detallepedido.domain.port.out.DetalleRepositoryPort;
import hexagonal.developer.shared.domain.model.PageDomain;
import hexagonal.developer.shared.fixture.DetalleFixture;
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
public class ListaDetalleUseCaseTest {

    @Mock
    private DetalleRepositoryPort detalleRepositoryPort;
    @InjectMocks
    private ListarDetalleUseCase listarDetalleUseCase;

    @Test
    @DisplayName("Debe lanzar un lista correcta de detalles")
    void listaCorrecta(){

        int pagina = 0;
        int tamanio = 10;
        PageDomain<DetallePedido> pageCorrecto = DetalleFixture.unPageDomainDetalles();

        when(detalleRepositoryPort.listar(pagina, tamanio)).thenReturn(pageCorrecto);

        PageDomain<DetallePedido> resultado = listarDetalleUseCase.listar(pagina, tamanio);

        assertEquals(2, resultado.contenido().size());
        assertEquals(0, resultado.paginaActual());
        assertEquals(1, resultado.totalPaginas());
        assertEquals(2L, resultado.totalElementos());

        verify(detalleRepositoryPort, times(1)).listar(pagina, tamanio);
    }
    @Test
    @DisplayName("debe retornar una lista vacía")
    void listarMalo(){

        int pagina = 0;
        int tamanio = 10;
        PageDomain<DetallePedido> pageVacio = new PageDomain<>(List.of(), 0, 0, 0L);

        when(detalleRepositoryPort.listar(pagina, tamanio)).thenReturn(pageVacio);

        PageDomain<DetallePedido> resultado = listarDetalleUseCase.listar(pagina, tamanio);

        assertNotNull(resultado);
        assertTrue(resultado.contenido().isEmpty());
        assertEquals(0L, resultado.totalElementos());

        verify(detalleRepositoryPort, times(1)).listar(pagina, tamanio);
    }




}
