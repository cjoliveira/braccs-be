package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.CicloIATF;

import java.util.List;
import java.util.Optional;

public interface CicloIATFService {

    CicloIATF salvar(CicloIATF cicloParam);

    CicloIATF atualizar(CicloIATF cicloParam);

    void deletar(CicloIATF cicloParam);

    List<CicloIATF> buscar(CicloIATF ciclo);

    Optional<CicloIATF> consultarPorId(Long id);

    CicloIATF buscar(Long id);
}
