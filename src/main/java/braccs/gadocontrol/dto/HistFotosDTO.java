package braccs.gadocontrol.dto;

import java.util.Date;

public class HistFotosDTO {

    private Long idAnimal;
    private Date dataFoto;
    private byte[] foto;

    public HistFotosDTO() {
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Date getDataFoto() {
        return dataFoto;
    }

    public void setDataFoto(Date dataFoto) {
        this.dataFoto = dataFoto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
