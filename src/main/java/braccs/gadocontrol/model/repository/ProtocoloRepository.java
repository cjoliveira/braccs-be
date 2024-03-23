package braccs.gadocontrol.model.repository;

import braccs.gadocontrol.keys.ProtocoloKey;
import braccs.gadocontrol.model.entity.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtocoloRepository extends JpaRepository<Protocolo, ProtocoloKey> {
}
