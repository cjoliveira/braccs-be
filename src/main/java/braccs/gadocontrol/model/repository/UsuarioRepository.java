package braccs.gadocontrol.model.repository;

import braccs.gadocontrol.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
