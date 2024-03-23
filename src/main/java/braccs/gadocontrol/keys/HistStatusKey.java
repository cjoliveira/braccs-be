package braccs.gadocontrol.keys;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class HistStatusKey implements Serializable {
    private Long animal;
    private Date dataStatus;

    public HistStatusKey(Long animal, Date dataStatus) {
        this.animal = animal;
        this.dataStatus = dataStatus;
    }

    public HistStatusKey() {
    }

    public Long getAnimal() {
        return this.animal;
    }

    public void setAnimal(Long animal) {
        this.animal = animal;
    }

    public Date getDataStatus() {
        return this.dataStatus;
    }

    public void setDataStatus(Date dataStatus) {
        this.dataStatus = dataStatus;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.dataStatus, this.animal});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            HistStatusKey other = (HistStatusKey)obj;
            return Objects.equals(this.dataStatus, other.dataStatus) && Objects.equals(this.animal, other.animal);
        }
    }
}