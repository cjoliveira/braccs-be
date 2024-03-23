package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.Protocolo;

import java.util.List;
import java.util.Optional;

public interface ProtocoloService {

    Protocolo salvar(Protocolo protocoloParam);

    Protocolo atualizar(Protocolo protocoloParam);

    void deletar(Protocolo protocoloParam);

    List<Protocolo> buscar(Protocolo protocolo);

    Optional<Protocolo> consultarPorId(Long idProtocolo, Long idCiclo);
}
