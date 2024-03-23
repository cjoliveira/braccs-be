package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    Animal salvar(Animal animalParam);

    Animal atualizar(Animal animalParam);

    void deletar(Animal animalParam);

    List<Animal> buscar(Animal animal);

    Optional<Animal> consultarPorId(long id);
}
