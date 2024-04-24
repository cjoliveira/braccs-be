package braccs.gadocontrol.model.repository;

import braccs.gadocontrol.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a WHERE a.dataCadastro BETWEEN :startDate AND :endDate AND a.statusAtual IN :statusList")
    List<Animal> findByDatePeriodAndStatus(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("statusList") List<String> statusList);
}
