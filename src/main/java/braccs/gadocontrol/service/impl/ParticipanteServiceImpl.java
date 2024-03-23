package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.keys.ParticipanteKey;
import braccs.gadocontrol.keys.ProtocoloKey;
import braccs.gadocontrol.model.entity.Participante;
import braccs.gadocontrol.model.repository.ParticipanteRepository;
import braccs.gadocontrol.service.ParticipanteService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {
    private final ParticipanteRepository repository;

    public ParticipanteServiceImpl(ParticipanteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Participante salvar(Participante participanteParam) {
        return this.repository.save(participanteParam);
    }

    @Override
    @Transactional
    public Participante atualizar(Participante participanteParam) {
        Objects.requireNonNull(participanteParam.getProtocolo());
        Objects.requireNonNull(participanteParam.getAnimal());
        return this.repository.save(participanteParam);
    }

    @Override
    @Transactional
    public void deletar(Participante participanteParam) {
        Objects.requireNonNull(participanteParam.getProtocolo());
        Objects.requireNonNull(participanteParam.getAnimal());
        this.repository.delete(participanteParam);
    }

    @Override
    @Transactional
    public List<Participante> buscar(Participante participanteParam) {
        Example<Participante> example = Example.of(participanteParam);
        return this.repository.findAll(example);
    }

    @Override
    @Transactional
    public Optional<Participante> consultarPorId(ProtocoloKey idProtocolo, Long idAnimal) {
        ParticipanteKey id = new ParticipanteKey(idProtocolo, idAnimal);
        return this.repository.findById(id);
    }
}
