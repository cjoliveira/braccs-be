package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.model.entity.CicloIATF;
import braccs.gadocontrol.model.repository.CicloIATFRepository;
import braccs.gadocontrol.service.CicloIATFService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class CicloIATFServiceImpl implements CicloIATFService {
    private final CicloIATFRepository repository;

    public CicloIATFServiceImpl(CicloIATFRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public CicloIATF salvar(CicloIATF cicloParam) {
        return this.repository.save(cicloParam);
    }

    @Override
    @Transactional
    public CicloIATF atualizar(CicloIATF cicloParam) {
        Objects.requireNonNull(cicloParam.getIdCiclo());
        return this.repository.save(cicloParam);
    }

    @Override
    @Transactional
    public void deletar(CicloIATF cicloParam) {
        Objects.requireNonNull(cicloParam.getIdCiclo());
        this.repository.delete(cicloParam);
    }

    @Override
    @Transactional
    public List<CicloIATF> buscar(CicloIATF ciclo) {
        Example<CicloIATF> example = Example.of(ciclo);
        return this.repository.findAll(example);
    }

    @Override
    @Transactional
    public Optional<CicloIATF> consultarPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    @Transactional
    public CicloIATF buscar(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("CicloIATF não encontrado"));
    }
}
