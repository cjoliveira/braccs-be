package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.HistFotos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HistFotosService {

    HistFotos salvar(HistFotos histFotosParam);

    HistFotos atualizar(HistFotos histFotosParam);

    void deletar(HistFotos histFotosParam);

    List<HistFotos> buscar(Long idAnimal);

    Optional<HistFotos> consultarPorId(Long idAnimal, Date dataFoto);
}
