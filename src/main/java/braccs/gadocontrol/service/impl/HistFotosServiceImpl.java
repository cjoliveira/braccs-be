package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.keys.HistFotosKey;
import braccs.gadocontrol.model.entity.Animal;
import braccs.gadocontrol.model.entity.HistFotos;
import braccs.gadocontrol.model.repository.HistFotosRepository;
import braccs.gadocontrol.service.AnimalService;
import braccs.gadocontrol.service.HistFotosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HistFotosServiceImpl implements HistFotosService {
    HistFotosRepository repository;

    @Autowired
    AnimalService animalService;

    public HistFotosServiceImpl(HistFotosRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public HistFotos salvar(HistFotos histFotosParam) {
        return (HistFotos)this.repository.save(histFotosParam);
    }

    @Transactional
    public HistFotos atualizar(HistFotos histFotosParam) {
        Objects.requireNonNull(histFotosParam.getAnimal());
        Objects.requireNonNull(histFotosParam.getDataFoto());
        return (HistFotos)this.repository.save(histFotosParam);
    }

    @Transactional
    public void deletar(HistFotos histFotosParam) {
        Objects.requireNonNull(histFotosParam.getAnimal());
        Objects.requireNonNull(histFotosParam.getDataFoto());
        this.repository.delete(histFotosParam);
    }

    @Transactional
    public List<HistFotos> buscar(Long idAnimal) {
        Animal animal = animalService.consultarPorId(idAnimal).orElse(null);
        List<HistFotos> histFotos = this.repository.findAllByAnimal(animal);
        histFotos.sort(Comparator.comparing(HistFotos::getDataFoto).reversed());
        return histFotos;
    }

    @Transactional
    public Optional<HistFotos> consultarPorId(Long idAnimal, Date dataFoto) {
        HistFotosKey id = new HistFotosKey(idAnimal, dataFoto);
        return this.repository.findById(id);
    }
}