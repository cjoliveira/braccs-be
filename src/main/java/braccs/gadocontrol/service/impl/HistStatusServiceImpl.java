package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.keys.HistStatusKey;
import braccs.gadocontrol.model.entity.HistStatus;
import braccs.gadocontrol.model.repository.HistStatusRepository;
import braccs.gadocontrol.service.HistStatusService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HistStatusServiceImpl implements HistStatusService {
    private HistStatusRepository repository;

    public HistStatusServiceImpl(HistStatusRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public HistStatus salvar(HistStatus histStatusParam) {
        return (HistStatus)this.repository.save(histStatusParam);
    }

    @Transactional
    public HistStatus atualizar(HistStatus histStatusParam) {
        Objects.requireNonNull(histStatusParam.getAnimal());
        Objects.requireNonNull(histStatusParam.getDataStatus());
        return (HistStatus)this.repository.save(histStatusParam);
    }

    @Transactional
    public void deletar(HistStatus histStatusParam) {
        Objects.requireNonNull(histStatusParam.getAnimal());
        Objects.requireNonNull(histStatusParam.getDataStatus());
        this.repository.delete(histStatusParam);
    }

    @Transactional
    public List<HistStatus> buscar(HistStatus histStatusParam) {
        Example example = Example.of(histStatusParam);
        return this.repository.findAll(example);
    }

    @Transactional
    public Optional<HistStatus> consultarPorId(Long idAnimal, Date dataStatus) {
        HistStatusKey id = new HistStatusKey(idAnimal, dataStatus);
        return this.repository.findById(id);
    }
}