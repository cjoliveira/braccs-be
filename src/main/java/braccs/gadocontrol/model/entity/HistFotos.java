package braccs.gadocontrol.model.entity;

import braccs.gadocontrol.keys.HistFotosKey;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        name = "HISTFOTOS",
        schema = "gado"
)
@IdClass(HistFotosKey.class)
public class HistFotos {

    @Id
    @ManyToOne
    @JoinColumn(
            name = "IDANIMAL"
    )
    private Animal animal;
    @Id
    @Column(
            name = "DATAFOTO"
    )
    private Date dataFoto;
    @Column(
            name = "FOTO"
    )
    private byte[] foto;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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
