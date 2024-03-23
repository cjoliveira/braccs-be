package braccs.gadocontrol.model.repository;

import braccs.gadocontrol.keys.VendaKey;
import braccs.gadocontrol.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, VendaKey> {

    Optional<Venda> findByNumVenda(Long numVenda);
}
