package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.Animal;
import braccs.gadocontrol.model.repository.AnimalRepository;
import braccs.gadocontrol.service.impl.AnimalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnimalServiceImplTest {

    @Mock
    private AnimalRepository repository;

    @InjectMocks
    private AnimalServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar o animal com sucesso")
    void shouldSaveAnimalSuccessfully() {
        Animal animal = new Animal();
        when(repository.save(any(Animal.class))).thenReturn(animal);

        Animal result = service.salvar(animal);

        assertEquals(animal, result);
        verify(repository, times(1)).save(animal);
    }

    @Test
    @DisplayName("Deve atualizar o animal com sucesso")
    void shouldUpdateAnimalSuccessfully() {
        Animal animal = new Animal();
        animal.setIdAnimal(1L);
        when(repository.save(any(Animal.class))).thenReturn(animal);

        Animal result = service.atualizar(animal);

        assertEquals(animal, result);
        verify(repository, times(1)).save(animal);
    }

    @Test
    @DisplayName("Deve excluir o animal com sucesso")
    void shouldDeleteAnimalSuccessfully() {
        Animal animal = new Animal();
        animal.setIdAnimal(1L);

        service.deletar(animal);

        verify(repository, times(1)).delete(animal);
    }

    @Test
    @DisplayName("Deve encontrar o animal por ID com sucesso")
    void shouldFindAnimalByIdSuccessfully() {
        Animal animal = new Animal();
        animal.setIdAnimal(1L);
        when(repository.findById(anyLong())).thenReturn(Optional.of(animal));

        Optional<Animal> result = service.consultarPorId(1L);

        assertEquals(animal, result.get());
        verify(repository, times(1)).findById(1L);
    }
}
