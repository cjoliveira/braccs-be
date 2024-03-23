package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.keys.HistFotosKey;
import braccs.gadocontrol.model.entity.HistFotos;
import braccs.gadocontrol.model.repository.HistFotosRepository;
import braccs.gadocontrol.service.HistFotosService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HistFotosServiceImpl implements HistFotosService {
    HistFotosRepository repository;

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
    public List<HistFotos> buscar(HistFotos histFotosParam) {
        Example example = Example.of(histFotosParam);
        return this.repository.findAll(example);
    }

    @Transactional
    public Optional<HistFotos> consultarPorId(Long idAnimal, Date dataFoto) {
        HistFotosKey id = new HistFotosKey(idAnimal, dataFoto);
        return this.repository.findById(id);
    }
}