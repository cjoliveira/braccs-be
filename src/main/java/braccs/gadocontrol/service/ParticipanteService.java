package braccs.gadocontrol.service;

import braccs.gadocontrol.keys.ProtocoloKey;
import braccs.gadocontrol.model.entity.Participante;

import java.util.List;
import java.util.Optional;

public interface ParticipanteService {

    Participante salvar(Participante participanteParam);

    Participante atualizar(Participante participanteParam);

    void deletar(Participante participanteParam);

    List<Participante> buscar(Participante participanteParam);

    Optional<Participante> consultarPorId(ProtocoloKey idProtocolo, Long idAnimal);
}
