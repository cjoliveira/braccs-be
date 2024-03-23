package braccs.gadocontrol.dto;

public class ParticipanteDTO {

    private Long idProtocolo;
    private Long idCiclo;
    private Long idAnimal;
    private Boolean aplicouHormonio;
    private String statusFecundacao;

    public ParticipanteDTO() {
    }

    public Long getIdProtocolo() {
        return idProtocolo;
    }

    public void setIdProtocolo(Long idProtocolo) {
        this.idProtocolo = idProtocolo;
    }

    public Long getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Long idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Boolean getAplicouHormonio() {
        return aplicouHormonio;
    }

    public void setAplicouHormonio(Boolean aplicouHormonio) {
        this.aplicouHormonio = aplicouHormonio;
    }

    public String getStatusFecundacao() {
        return statusFecundacao;
    }

    public void setStatusFecundacao(String statusFecundacao) {
        this.statusFecundacao = statusFecundacao;
    }
}
