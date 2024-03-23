package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.keys.VendaKey;
import braccs.gadocontrol.model.entity.Venda;
import braccs.gadocontrol.model.repository.VendaRepository;
import braccs.gadocontrol.model.strategy.PrecoAltaQtdStrategy;
import braccs.gadocontrol.model.strategy.PrecoRegularStrategy;
import braccs.gadocontrol.service.VendaService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendaServiceImpl implements VendaService {
    private final VendaRepository repository;

    public VendaServiceImpl(VendaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Venda salvar(Venda vendaParam) {
        return this.repository.save(vendaParam);
    }

    @Override
    @Transactional
    public Venda atualizar(Venda vendaParam) {
        Objects.requireNonNull(vendaParam.getAnimal());
        Objects.requireNonNull(vendaParam.getUsuario());
        Objects.requireNonNull(vendaParam.getCliente());
        return this.repository.save(vendaParam);
    }

    @Override
    @Transactional
    public void deletar(Venda vendaParam) {
        Objects.requireNonNull(vendaParam.getAnimal());
        Objects.requireNonNull(vendaParam.getUsuario());
        Objects.requireNonNull(vendaParam.getCliente());
        this.repository.delete(vendaParam);
    }

    @Override
    @Transactional
    public List<Venda> buscar(Venda venda) {
        Example<Venda> example = Example.of(venda);
        return this.repository.findAll(example);
    }

    @Override
    @Transactional
    public Optional<Venda> consultarPorId(Long idAnimal, Long idUsuario, Long idCliente) {
        VendaKey id = new VendaKey(idAnimal, idUsuario, idCliente);
        return this.repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Venda> consultarPorNumVenda(Long numVenda) {
        return this.repository.findByNumVenda(numVenda);
    }
}
