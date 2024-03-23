package braccs.gadocontrol.service;

import braccs.gadocontrol.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario salvar(Usuario usuarioParam);

    Usuario atualizar(Usuario usuarioParam);

    void deletar(Usuario usuarioParam);

    List<Usuario> buscar(Usuario usuario);

    Optional<Usuario> consultarPorId(Long id);
}
