package braccs.gadocontrol.keys;

import java.io.Serializable;
import java.util.Objects;

public class ParticipanteKey implements Serializable {
    private ProtocoloKey protocolo;
    private Long animal;

    public ParticipanteKey(ProtocoloKey protocolo, Long animal) {
        this.protocolo = protocolo;
        this.animal = animal;
    }

    public ParticipanteKey() {
    }

    public ProtocoloKey getProtocolo() {
        return this.protocolo;
    }

    public void setProtocolo(ProtocoloKey protocolo) {
        this.protocolo = protocolo;
    }

    public Long getAnimal() {
        return this.animal;
    }

    public void setAnimal(Long animal) {
        this.animal = animal;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.animal, this.protocolo});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            ParticipanteKey other = (ParticipanteKey)obj;
            return Objects.equals(this.animal, other.animal) && Objects.equals(this.protocolo, other.protocolo);
        }
    }
}