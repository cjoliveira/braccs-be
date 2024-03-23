package braccs.gadocontrol.model.repository;

import braccs.gadocontrol.keys.ParticipanteKey;
import braccs.gadocontrol.model.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, ParticipanteKey> {
}

