package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.model.entity.Animal;
import braccs.gadocontrol.model.repository.AnimalRepository;
import braccs.gadocontrol.service.AnimalService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository repository;

    public AnimalServiceImpl(AnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Animal salvar(Animal animalParam) {
        if (animalParam.getPreco() != null) {
            Random random = new Random();
            Long num = random.nextLong(90000000L) + 10000000L;
            animalParam.setNumCompra(num);
        }
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
        List<Animal> animais = this.repository.findAll(example);
        animais.sort(Comparator.comparing(Animal::getDataCadastro).reversed());
        return animais;
    }

    @Override
    @Transactional
    public Optional<Animal> consultarPorId(long id) {
        return this.repository.findById(id);
    }

    @Override
    @Transactional
    public List<Animal> buscarAnimaisDisponiveisPorData(String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDateFormatted;
        Date endDateFormatted;
        try{
            startDateFormatted = formatter.parse(startDate);
            endDateFormatted = formatter.parse(endDate);
        } catch (Exception e) {
            throw new RuntimeException("Data inválida!");
        }

        return this.repository.findByDatePeriodAndStatus(startDateFormatted, endDateFormatted, List.of("Normal", "Doente", "Prenha", "Comprado"));
    }

    @Override
    public String getAnimaisAsCsv(String dataInicio, String dataFim) {
        List<Animal> animais = buscarAnimaisDisponiveisPorData(dataInicio, dataFim);

        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("idAnimal,numId,tipo,dataNasc,peso,statusAtual,preco,dataCadastro,genero,numCompra\n");
        for (Animal animal : animais) {
            csvBuilder.append(animal.getIdAnimal()).append(",");
            csvBuilder.append(animal.getNumId()).append(",");
            csvBuilder.append(animal.getTipo()).append(",");
            csvBuilder.append(animal.getDataNasc()).append(",");
            csvBuilder.append(animal.getPeso()).append(",");
            csvBuilder.append(animal.getStatusAtual()).append(",");
            csvBuilder.append(animal.getPreco()).append(",");
            csvBuilder.append(animal.getDataCadastro()).append(",");
            csvBuilder.append(animal.getGenero()).append(",");
            csvBuilder.append(animal.getNumCompra()).append("\n");
        }

        return csvBuilder.toString();
    }
}
