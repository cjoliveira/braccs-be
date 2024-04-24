package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.Venda;

import java.util.List;
import java.util.Optional;

public interface VendaService {

    Venda salvar(Venda vendaParam);

    Venda atualizar(Venda vendaParam);

    void deletar(Venda vendaParam);

    List<Venda> buscar(Venda venda);

    Optional<Venda> consultarPorId(Long idAnimal, Long idUsuario, Long idCliente);

    Optional<Venda> consultarPorNumVenda(Long numVenda);
}
