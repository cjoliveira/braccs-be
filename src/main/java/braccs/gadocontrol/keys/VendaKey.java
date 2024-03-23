package braccs.gadocontrol.keys;

import java.io.Serializable;

public class VendaKey implements Serializable {
    private Long animal;
    private Long usuario;
    private Long cliente;

    public VendaKey(Long animal, Long usuario, Long cliente) {
        this.animal = animal;
        this.usuario = usuario;
        this.cliente = cliente;
    }

    public VendaKey() {
    }

    public Long getAnimal() {
        return this.animal;
    }

    public void setAnimal(Long animal) {
        this.animal = animal;
    }

    public Long getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Long getCliente() {
        return this.cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }
}