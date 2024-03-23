package braccs.gadocontrol.model.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "CLIENTES",
        schema = "gado"
)
public class Cliente {

    @Id
    @Column(
            name = "IDCLIENTE"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idCliente;
    @Column(
            name = "NOME",
            nullable = false
    )
    private String nome;
    @Column(
            name = "EMAIL",
            nullable = false
    )
    private String emailCliente;
    @Column(
            name = "CPFCNPJ",
            nullable = false
    )
    private String cpfCnpj;
    @Column(
            name = "TELEFONE",
            nullable = false
    )
    private String telefone;

    public Cliente() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
