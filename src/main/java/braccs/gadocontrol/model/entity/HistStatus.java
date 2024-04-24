package braccs.gadocontrol.model.entity;


import braccs.gadocontrol.keys.HistStatusKey;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        name = "HISTSTATUS",
        schema = "gado"
)
@IdClass(HistStatusKey.class)
public class HistStatus {

    @Id
    @ManyToOne
    @JoinColumn(
            name = "IDANIMAL"
    )
    private Animal animal;
    @Id
    @Column(
            name = "DATASTATUS"
    )
    private Date dataStatus;
    @Column(
            name = "STATUSANIMAL"
    )
    private String statusAnimal;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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
