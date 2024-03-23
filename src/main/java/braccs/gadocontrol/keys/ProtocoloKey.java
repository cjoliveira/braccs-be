package braccs.gadocontrol.keys;

import java.io.Serializable;
import java.util.Objects;

public class ProtocoloKey implements Serializable {
    private Long idProtocolo;
    private Long ciclo;

    public ProtocoloKey(Long idProtocolo, Long ciclo) {
        this.idProtocolo = idProtocolo;
        this.ciclo = ciclo;
    }

    public ProtocoloKey() {
    }

    public Long getIdProtocolo() {
        return this.idProtocolo;
    }

    public void setIdProtocolo(Long idProtocolo) {
        this.idProtocolo = idProtocolo;
    }

    public Long getCiclo() {
        return this.ciclo;
    }

    public void setCiclo(Long ciclo) {
        this.ciclo = ciclo;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.idProtocolo, this.ciclo});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            ProtocoloKey other = (ProtocoloKey)obj;
            return Objects.equals(this.idProtocolo, other.idProtocolo) && Objects.equals(this.ciclo, other.ciclo);
        }
    }
}
