package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente salvar(Cliente clienteParam);

    Cliente atualizar(Cliente clienteParam);

    void deletar(Cliente clienteParam);

    List<Cliente> buscar(Cliente cliente);

    Optional<Cliente> consultarPorId(long id);
}
