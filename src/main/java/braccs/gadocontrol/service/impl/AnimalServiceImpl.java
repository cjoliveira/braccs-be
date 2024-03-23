package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.model.entity.Animal;
import braccs.gadocontrol.model.repository.AnimalRepository;
import braccs.gadocontrol.service.AnimalService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository repository;

    public AnimalServiceImpl(AnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Animal salvar(Animal animalParam) {
        return this.repository.save(animalParam);
    }

    @Override
    @Transactional
    public Animal atualizar(Animal animalParam) {
        Objects.requireNonNull(animalParam.getIdAnimal());
        return this.repository.save(animalParam);
    }

    @Override
    @Transactional
    public void deletar(Animal animalParam) {
        Objects.requireNonNull(animalParam.getIdAnimal());
        this.repository.delete(animalParam);
    }

    @Override
    @Transactional
    public List<Animal> buscar(Animal animal) {
        Example<Animal> example = Example.of(animal);
        return this.repository.findAll(example);
    }

    @Override
    @Transactional
    public Optional<Animal> consultarPorId(long id) {
        return this.repository.findById(id);
    }
}
