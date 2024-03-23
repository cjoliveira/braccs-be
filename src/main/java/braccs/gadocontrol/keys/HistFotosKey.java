package braccs.gadocontrol.keys;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class HistFotosKey implements Serializable {

    private Long animal;
    private Date dataFoto;

    public HistFotosKey(Long animal, Date dataFoto) {
        this.animal = animal;
        this.dataFoto = dataFoto;
    }

    public HistFotosKey() {
    }

    public Long getAnimal() {
        return this.animal;
    }

    public void setAnimal(Long animal) {
        this.animal = animal;
    }

    public Date getDataFoto() {
        return this.dataFoto;
    }

    public void setDataFoto(Date dataFoto) {
        this.dataFoto = dataFoto;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.dataFoto, this.animal});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            HistFotosKey other = (HistFotosKey)obj;
            return Objects.equals(this.dataFoto, other.dataFoto) && Objects.equals(this.animal, other.animal);
        }
    }
}
