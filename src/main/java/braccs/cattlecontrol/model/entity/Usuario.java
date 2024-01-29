package braccs.cattlecontrol.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "USUARIO", schema = "gado")
public class Usuario {

    @Id
    @Column(name = "IDUSUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "LOGIN", nullable = false)
    private String logIn;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "DATANASC", nullable = false)
    private Date dataNasc;

    @Column(name = "DATACADAST", nullable = false)
    private Date dataCadast;

    @Column(name = "PERFIL", nullable = false)
    private String perfil;

    @Column(name = "EMAIL", nullable = false)
    private String emailUsuario;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogIn() {
        return logIn;
    }

    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Date getDataCadast() {
        return dataCadast;
    }

    public void setDataCadast(Date dataCadast) {
        this.dataCadast = dataCadast;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
