package braccs.cattlecontrol.service;

import braccs.cattlecontrol.dto.AnimalDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public interface AnimalService {

    AnimalDTO salvar(AnimalDTO animalDTO);

    AnimalDTO atualizar(Long id, AnimalDTO animal);

    void deletar(Long id);

    AnimalDTO buscar(Long id);

    List<AnimalDTO> buscarAnimais();

    List<AnimalDTO> buscarAnimaisPorFiltro(Long idMae, Long idUsuario, String numId, String tipo, LocalDateTime dataNasc,
                                           Long peso, String statusAtual, Long preco, LocalDateTime dataCadastro);

}
