package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.keys.ProtocoloKey;
import braccs.gadocontrol.model.entity.Protocolo;
import braccs.gadocontrol.model.repository.ProtocoloRepository;
import braccs.gadocontrol.service.ProtocoloService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProtocoloServiceImpl implements ProtocoloService {
    private final ProtocoloRepository repository;

    public ProtocoloServiceImpl(ProtocoloRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Protocolo salvar(Protocolo protocoloParam) {
        return this.repository.save(protocoloParam);
    }

    @Override
    @Transactional
    public Protocolo atualizar(Protocolo protocoloParam) {
        Objects.requireNonNull(protocoloParam.getIdProtocolo());
        Objects.requireNonNull(protocoloParam.getCiclo());
        return this.repository.save(protocoloParam);
    }

    @Override
    @Transactional
    public void deletar(Protocolo protocoloParam) {
        Objects.requireNonNull(protocoloParam.getIdProtocolo());
        Objects.requireNonNull(protocoloParam.getCiclo());
        this.repository.delete(protocoloParam);
    }

    @Override
    @Transactional
    public List<Protocolo> buscar(Protocolo protocolo) {
        Example<Protocolo> example = Example.of(protocolo);
        return this.repository.findAll(example);
    }

    @Override
    @Transactional
    public Optional<Protocolo> consultarPorId(Long idProtocolo, Long idCiclo) {
        ProtocoloKey id = new ProtocoloKey(idProtocolo, idCiclo);
        return this.repository.findById(id);
    }
}
