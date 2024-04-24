package braccs.gadocontrol.model.repository;

import braccs.gadocontrol.keys.HistFotosKey;
import braccs.gadocontrol.model.entity.Animal;
import braccs.gadocontrol.model.entity.HistFotos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistFotosRepository extends JpaRepository<HistFotos, HistFotosKey> {

    List<HistFotos> findAllByAnimal(Animal idAnimal);
}
