package braccs.cattlecontrol.service.impl;

import braccs.cattlecontrol.dto.UsuarioDTO;
import braccs.cattlecontrol.model.entity.Usuario;
import braccs.cattlecontrol.model.repository.UsuarioRepository;
import braccs.cattlecontrol.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO cadastrar(UsuarioDTO dto) {
        return null;
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        return null;
    }

    @Override
    public void deletar(Usuario usuario) {

    }

    @Override
    public Usuario buscar(Usuario usuario) {
        return null;
    }


    public Usuario consultarPorId(long id) {
        return usuarioRepository.findByIdUsuario(id);
    }
}
