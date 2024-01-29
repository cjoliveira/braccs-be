package braccs.cattlecontrol.service;

import braccs.cattlecontrol.dto.UsuarioDTO;
import braccs.cattlecontrol.model.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    UsuarioDTO cadastrar(UsuarioDTO dto);

    Usuario atualizar(Usuario usuario);

    void deletar(Usuario usuario);

    Usuario buscar(Usuario usuario);

    Usuario consultarPorId(long id);


}
