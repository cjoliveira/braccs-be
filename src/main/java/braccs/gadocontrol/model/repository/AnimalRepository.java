package braccs.gadocontrol.model.repository;

import braccs.gadocontrol.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
