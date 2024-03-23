package braccs.gadocontrol.model.entity;

import braccs.gadocontrol.keys.ParticipanteKey;
import jakarta.persistence.*;

@Entity
@Table(
        name = "PARTICIPANTES",
        schema = "gado"
)
@IdClass(ParticipanteKey.class)
public class Participante {

    @Id
    @ManyToOne
    @JoinColumn(
            name = "IDANIMAL"
    )
    private Animal animal;
    @Id
    @ManyToOne
    @JoinColumns({@JoinColumn(
            name = "IDPROTOCOLO",
            referencedColumnName = "IDPROTOCOLO"
    ), @JoinColumn(
            name = "IDCICLO",
            referencedColumnName = "IDCICLO"
    )})
    private Protocolo protocolo;
    @Column(
            name = "APLICHORMONIO"
    )
    private Boolean aplicouHormonio;
    @Column(
            name = "STATUSFECUND"
    )
    private String sastusFecundacao;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public Boolean getAplicouHormonio() {
        return aplicouHormonio;
    }

    public void setAplicouHormonio(Boolean aplicouHormonio) {
        this.aplicouHormonio = aplicouHormonio;
    }

    public String getSastusFecundacao() {
        return sastusFecundacao;
    }

    public void setSastusFecundacao(String sastusFecundacao) {
        this.sastusFecundacao = sastusFecundacao;
    }
}
