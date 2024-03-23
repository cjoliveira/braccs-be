package braccs.gadocontrol.dto;

import java.util.Date;

public class HistStatusDTO {

    private Long idAnimal;
    private Date dataStatus;
    private String statusAnimal;

    public HistStatusDTO() {
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Date getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Date dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getStatusAnimal() {
        return statusAnimal;
    }

    public void setStatusAnimal(String statusAnimal) {
        this.statusAnimal = statusAnimal;
    }
}
