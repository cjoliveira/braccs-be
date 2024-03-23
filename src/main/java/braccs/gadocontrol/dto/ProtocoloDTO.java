package braccs.gadocontrol.dto;

import java.util.Date;

public class ProtocoloDTO {

    private Long idProtocolo;
    private Long idCiclo;
    private Date dataInicio;
    private Date dataFim;
    private String statusProtocolo;

    public ProtocoloDTO() {
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
