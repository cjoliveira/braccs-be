package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.CicloIATF;
import braccs.gadocontrol.model.repository.CicloIATFRepository;
import braccs.gadocontrol.service.impl.CicloIATFServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CicloIATFServiceImplTest {

    @Mock
    private CicloIATFRepository repository;

    @InjectMocks
    private CicloIATFServiceImpl cicloIATFService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar o CicloIATF com sucesso")
    void shouldSaveCicloIATFSuccessfully() {
        CicloIATF ciclo = new CicloIATF();
        when(repository.save(any(CicloIATF.class))).thenReturn(ciclo);

        CicloIATF result = cicloIATFService.salvar(ciclo);

        assertEquals(ciclo, result);
        verify(repository, times(1)).save(ciclo);
    }

    @Test
    @DisplayName("Deve atualizar o CicloIATF com sucesso")
    void shouldUpdateCicloIATFSuccessfully() {
        CicloIATF ciclo = new CicloIATF();
        ciclo.setIdCiclo(1L);
        when(repository.save(any(CicloIATF.class))).thenReturn(ciclo);

        CicloIATF result = cicloIATFService.atualizar(ciclo);

        assertEquals(ciclo, result);
        verify(repository, times(1)).save(ciclo);
    }

    @Test
    @DisplayName("Deve excluir o CicloIATF com sucesso")
    void shouldDeleteCicloIATFSuccessfully() {
        CicloIATF ciclo = new CicloIATF();
        ciclo.setIdCiclo(1L);

        cicloIATFService.deletar(ciclo);

        verify(repository, times(1)).delete(ciclo);
    }

    @Test
    @DisplayName("Deve encontrar o CicloIATF por ID com sucesso")
    void shouldFindCicloIATFByIdSuccessfully() {
        CicloIATF ciclo = new CicloIATF();
        ciclo.setIdCiclo(1L);
        when(repository.findById(anyLong())).thenReturn(Optional.of(ciclo));

        Optional<CicloIATF> result = cicloIATFService.consultarPorId(1L);

        assertEquals(ciclo, result.get());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando o CicloIATF não for encontrado")
    void shouldThrowExceptionWhenCicloIATFNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> cicloIATFService.buscar(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve encontrar o CicloIATF por Example com sucesso")
    void shouldFindCicloIATFByExampleSuccessfully() {
        CicloIATF ciclo = new CicloIATF();
        when(repository.findAll(any(Example.class))).thenReturn(Collections.singletonList(ciclo));

        List<CicloIATF> result = cicloIATFService.buscar(ciclo);

        assertFalse(result.isEmpty());
        assertEquals(ciclo, result.get(0));
        verify(repository, times(1)).findAll(any(Example.class));
    }
}
