package braccs.gadocontrol.model.repository;

import braccs.gadocontrol.keys.HistStatusKey;
import braccs.gadocontrol.model.entity.HistStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistStatusRepository extends JpaRepository<HistStatus, HistStatusKey> {
}
