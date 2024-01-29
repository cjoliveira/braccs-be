package braccs.cattlecontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class UsuarioDTO {

    @JsonProperty("id_usuario")
    private Long idUsuario;

    private String nome;

    @JsonProperty("dat_nasc")
    private Date dataNasc;

    @JsonProperty("data_cadastro")
    private Date dataCadastro;

    private String perfil;

    @JsonProperty("email_usuario")
    private String emailUsuario;

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

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
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

}
