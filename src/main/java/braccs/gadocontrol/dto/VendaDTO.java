package braccs.gadocontrol.dto;

import java.util.Date;

public class VendaDTO {

    private Long idAnimal;
    private Long idUsuario;
    private Long idCliente;
    private Double preco;
    private Date dataVenda;
    private Long numVenda;

    public VendaDTO() {
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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
