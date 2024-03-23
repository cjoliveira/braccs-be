package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.HistStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HistStatusService {

    HistStatus salvar(HistStatus histStatusParam);

    HistStatus atualizar(HistStatus histStatusParam);

    void deletar(HistStatus histStatusParam);

    List<HistStatus> buscar(HistStatus histStatusParam);

    Optional<HistStatus> consultarPorId(Long idAnimal, Date dataStatus);
}
