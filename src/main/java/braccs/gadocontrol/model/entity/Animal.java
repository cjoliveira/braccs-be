package braccs.gadocontrol.model.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(
        name = "ANIMAIS",
        schema = "gado"
)
public class Animal {

    @Id
    @Column(name = "IDANIMAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimal;

    @ManyToOne
    @JoinColumn(name = "IDMAE", nullable = true)
    private Animal mae;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO")
    private Usuario usuarioCadastro;

    @Column(name = "NUMID", nullable = false)
    private String numId;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "DATANASC", nullable = false)
    private Date dataNasc;

    @Column(name = "PESO", nullable = false)
    private Long peso;

    @Column(name = "STATUSATUAL", nullable = false)
    private String statusAtual;

    @Column(name = "PRECO", nullable = true)
    private Long preco;

    @Column(name = "DATACADASTRO", nullable = false)
    private Date dataCadastro;

    @Column(name = "GENERO", nullable = false)
    private String genero;

    @Column(name = "NUMCOMPRA", nullable = true)
    private Long numCompra;

    public Animal() {
    }

    public Long getIdAnimal() {
        return this.idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Animal getMae() {
        return this.mae;
    }

    public void setMae(Animal mae) {
        this.mae = mae;
    }

    public Usuario getUsuarioCadastro() {
        return this.usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public String getNumId() {
        return this.numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Long getPeso() {
        return this.peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getStatusAtual() {
        return this.statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
        this.statusAtual = statusAtual;
    }

    public Long getPreco() {
        return this.preco;
    }

    public void setPreco(Long preco) {
        this.preco = preco;
    }

    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Long getNumCompra() {
        return this.numCompra;
    }

    public void setNumCompra(Long numCompra) {
        this.numCompra = numCompra;
    }
}
