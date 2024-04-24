package braccs.gadocontrol.dto;

import braccs.gadocontrol.model.entity.Usuario;

import java.util.Date;

public class UsuarioDTO {

    private String nome;
    private Date dataNasc;
    private Date dataCadast;
    private String perfil;
    private String emailUsuario;
    private String cpf;

    public UsuarioDTO() {
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


    public Usuario converterParaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setDataNasc(this.dataNasc);
        usuario.setDataCadast(this.dataCadast);
        usuario.setPerfil(this.perfil);
        usuario.setEmailUsuario(this.emailUsuario);
        usuario.setCpf(this.cpf);

        return usuario;
    }

    public Usuario converterParaUsuarioComSenha(String login, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setDataNasc(this.dataNasc);
        usuario.setDataCadast(this.dataCadast);
        usuario.setPerfil(this.perfil);
        usuario.setEmailUsuario(this.emailUsuario);
        usuario.setCpf(this.cpf);

        return usuario;
    }
}
