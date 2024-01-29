package braccs.cattlecontrol.model.repository;

import braccs.cattlecontrol.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByIdUsuario(long id);
}
