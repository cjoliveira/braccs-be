package braccs.gadocontrol.dto;

import java.util.Date;

public class CicloIATFDTO {

    private Long idCiclo;
    private Date dataInicio;
    private Date dataFim;
    private String statusCiclo;
    private Long dg;

    public CicloIATFDTO() {
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
