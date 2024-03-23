package braccs.gadocontrol.model.entity;

import braccs.gadocontrol.keys.VendaKey;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        name = "VENDAS",
        schema = "gado"
)
@IdClass(VendaKey.class)
public class Venda {

    @Id
    @ManyToOne
    @JoinColumn(
            name = "IDANIMAL"
    )
    private Animal animal;
    @Id
    @ManyToOne
    @JoinColumn(
            name = "IDUSUARIO"
    )
    private Usuario usuario;
    @Id
    @ManyToOne
    @JoinColumn(
            name = "IDCLIENTE"
    )
    private Cliente cliente;
    @Column(
            name = "PRECO"
    )
    private Double preco;
    @Column(
            name = "DATAVENDA"
    )
    private Date dataVenda;
    @Column(
            name = "NUMVENDA"
    )
    private Long numVenda;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Long getNumVenda() {
        return numVenda;
    }

    public void setNumVenda(Long numVenda) {
        this.numVenda = numVenda;
    }
}
