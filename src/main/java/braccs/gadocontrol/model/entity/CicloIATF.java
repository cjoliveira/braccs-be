package braccs.gadocontrol.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        name = "CICLOSIATF",
        schema = "gado"
)
public class CicloIATF {

    @Id
    @Column(
            name = "IDCICLO"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idCiclo;
    @Column(
            name = "DATAINICIO"
    )
    private Date dataInicio;
    @Column(
            name = "DATAFIM"
    )
    private Date dataFim;
    @Column(
            name = "STATUSCICLO"
    )
    private String statusCiclo;
    @Column(
            name = "DG"
    )
    private Long dg;

    public CicloIATF() {
    }

    public Long getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Long idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatusCiclo() {
        return statusCiclo;
    }

    public void setStatusCiclo(String statusCiclo) {
        this.statusCiclo = statusCiclo;
    }

    public Long getDg() {
        return dg;
    }

    public void setDg(Long dg) {
        this.dg = dg;
    }
}
