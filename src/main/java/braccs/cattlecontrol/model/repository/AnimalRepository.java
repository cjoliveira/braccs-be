package braccs.cattlecontrol.model.repository;

import braccs.cattlecontrol.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal findByIdAnimal(long idAnimal);

    @Query("SELECT a FROM Animal a WHERE " +
            "(:idMae IS NULL OR a.idMae.idAnimal = :idMae) AND " +
            "(:idUsuario IS NULL OR a.idUsuario.idUsuario = :idUsuario) AND " +
            "(:numId IS NULL OR a.numId = :numId) AND " +
            "(:tipo IS NULL OR a.tipo = :tipo) AND " +
            "(cast(:dataNasc as date) IS NULL OR a.dataNasc = :dataNasc) AND " +
            "(:peso IS NULL OR a.peso = :peso) AND " +
            "(:statusAtual IS NULL OR a.statusAtual = :statusAtual) AND " +
            "(:preco IS NULL OR a.preco = :preco) AND " +
            "(cast(:dataCadastro as date) IS NULL OR a.dataCadastro = :dataCadastro)")
    List<Animal> buscarPorFiltros(@Param("idMae") Long idMae,
                                  @Param("idUsuario") Long idUsuario,
                                  @Param("numId") String numId,
                                  @Param("tipo") String tipo,
                                  @Param("dataNasc") LocalDateTime dataNasc,
                                  @Param("peso") Long peso,
                                  @Param("statusAtual") String statusAtual,
                                  @Param("preco") Long preco,
                                  @Param("dataCadastro") LocalDateTime dataCadastro);
}
