package braccs.cattlecontrol.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ANIMAL", schema = "gado")
public class Animal {

    @Id
    @Column(name = "IDANIMAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimal;

    @ManyToOne
    @JoinColumn(name = "IDMAE")
    private Animal idMae;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO")
    private Usuario idUsuario;

    @Column(name = "NUMID", nullable = false)
    private String numId;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "DATANASC", nullable = false)
    private LocalDateTime dataNasc;

    @Column(name = "PESO", nullable = false)
    private Long peso;

    @Column(name = "STATUSATUAL", nullable = false)
    private String statusAtual;

    @Column(name = "PRECO", nullable = false)
    private Long preco;

    @Column(name = "DATACADASTRO", nullable = false)
    private LocalDateTime dataCadastro;

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Animal getIdMae() {
        return idMae;
    }

    public void setIdMae(Animal idMae) {
        this.idMae = idMae;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDateTime dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
        this.statusAtual = statusAtual;
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        this.preco = preco;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
