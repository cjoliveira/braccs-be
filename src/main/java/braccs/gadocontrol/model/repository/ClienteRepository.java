package braccs.gadocontrol.model.repository;

import braccs.gadocontrol.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
