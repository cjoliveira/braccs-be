package braccs.gadocontrol.service.impl;

import braccs.gadocontrol.model.entity.Usuario;
import braccs.gadocontrol.model.repository.UsuarioRepository;
import braccs.gadocontrol.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Usuario salvar(Usuario usuarioParam) {
        return this.repository.save(usuarioParam);
    }

    @Override
    @Transactional
    public Usuario atualizar(Usuario usuarioParam) {
        Objects.requireNonNull(usuarioParam.getIdUsuario());
        return this.repository.save(usuarioParam);
    }

    @Override
    @Transactional
    public void deletar(Usuario usuarioParam) {
        Objects.requireNonNull(usuarioParam.getIdUsuario());
        this.repository.delete(usuarioParam);
    }

    @Override
    @Transactional
    public List<Usuario> buscar(Usuario usuario) {
        Example<Usuario> example = Example.of(usuario);
        return this.repository.findAll(example);
    }

    @Override
    @Transactional
    public Optional<Usuario> consultarPorId(Long id) {
        return this.repository.findById(id);
    }
}
