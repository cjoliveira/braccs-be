package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.model.entity.Cliente;
import braccs.gadocontrol.model.repository.ClienteRepository;
import braccs.gadocontrol.service.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Cliente salvar(Cliente clienteParam) {
        return this.repository.save(clienteParam);
    }

    @Override
    @Transactional
    public Cliente atualizar(Cliente clienteParam) {
        Objects.requireNonNull(clienteParam.getIdCliente());
        return this.repository.save(clienteParam);
    }

    @Override
    @Transactional
    public void deletar(Cliente clienteParam) {
        Objects.requireNonNull(clienteParam.getIdCliente());
        this.repository.delete(clienteParam);
    }

    @Override
    @Transactional
    public List<Cliente> buscar(Cliente cliente) {
        Example<Cliente> example = Example.of(cliente);
        return this.repository.findAll(example);
    }

    @Override
    @Transactional
    public Optional<Cliente> consultarPorId(long id) {
        return this.repository.findById(id);
    }
}
