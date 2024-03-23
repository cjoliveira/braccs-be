package braccs.gadocontrol.model.entity;

import braccs.gadocontrol.keys.ProtocoloKey;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        name = "PROTOCOLOS",
        schema = "gado"
)
@IdClass(ProtocoloKey.class)
public class Protocolo {

    @Id
    @Column(
            name = "IDPROTOCOLO"
    )
    private Long idProtocolo;
    @Id
    @ManyToOne
    @JoinColumn(
            name = "IDCICLO"
    )
    private CicloIATF ciclo;
    @Column(
            name = "DATAINICIO"
    )
    private Date dataInicio;
    @Column(
            name = "DATAFIM"
    )
    private Date dataFim;
    @Column(
            name = "STATUSPROTOCOLO"
    )
    private String statusProtocolo;


    public Long getIdProtocolo() {
        return idProtocolo;
    }

    public void setIdProtocolo(Long idProtocolo) {
        this.idProtocolo = idProtocolo;
    }

    public CicloIATF getCiclo() {
        return ciclo;
    }

    public void setCiclo(CicloIATF ciclo) {
        this.ciclo = ciclo;
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

    public String getStatusProtocolo() {
        return statusProtocolo;
    }

    public void setStatusProtocolo(String statusProtocolo) {
        this.statusProtocolo = statusProtocolo;
    }
}
