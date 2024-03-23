package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.UsuarioDTO;
import braccs.gadocontrol.model.entity.Usuario;
import braccs.gadocontrol.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/gado/usuario"})
@CrossOrigin(
        origins = {"http://127.0.0.1:3000"}
)
public class UsuarioController {
    UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    private Usuario converter(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setLogIn(dto.getLogIn());
        usuario.setSenha(dto.getSenha());
        usuario.setDataNasc(dto.getDataNasc());
        usuario.setDataCadast(dto.getDataCadast());
        usuario.setPerfil(dto.getPerfil());
        usuario.setEmailUsuario(dto.getEmailUsuario());
        usuario.setCpf(dto.getCpf());
        if (dto.getIdUsuario() != null) {
            usuario.setIdUsuario(dto.getIdUsuario());
        }

        return usuario;
    }

    @PostMapping({"/salvar-usuario"})
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuario = this.converter(dto);
            usuario = this.service.salvar(usuario);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException var3) {
            return ResponseEntity.badRequest().body(var3.getMessage());
        }
    }

    @DeleteMapping({"/deletar-usuario/{IDUSUARIO}"})
    public ResponseEntity deletar(@PathVariable("IDUSUARIO") long idUsuario) {
        return this.service.consultarPorId(idUsuario).map((entity) -> {
            this.service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> ResponseEntity.badRequest().body("O id do usuário informado não foi encontrado na base de dados, por isso não pode ser excluído."));
    }

    @PutMapping({"/atualizar-usuario/{IDUSUARIO}"})
    public ResponseEntity atualizar(@PathVariable("IDUSUARIO") long idUsuario, @RequestBody UsuarioDTO dto) {
        return this.service.consultarPorId(idUsuario).map((entity) -> {
            try {
                Usuario usuario = this.converter(dto);
                usuario.setIdUsuario(entity.getIdUsuario());
                this.service.atualizar(usuario);
                return ResponseEntity.ok(usuario);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do usuário informado não foi encontrado na base de dados");
        });
    }

    @GetMapping({"/buscar-usuarios"})
    public ResponseEntity buscar(@RequestParam(value = "NOME",required = false) String nome, @RequestParam(value = "LOGIN",required = false) String logIn, @RequestParam(value = "SENHA",required = false) String senha, @RequestParam(value = "DATANASC",required = false) Date dataNasc, @RequestParam(value = "DATACADAST",required = false) Date dataCadast, @RequestParam(value = "PERFIL",required = false) String perfil, @RequestParam(value = "EMAIL",required = false) String emailUsuario, @RequestParam(value = "CPF",required = false) String cpf) {
        Usuario usuariosFiltro = new Usuario();
        usuariosFiltro.setNome(nome);
        usuariosFiltro.setLogIn(logIn);
        usuariosFiltro.setSenha(senha);
        usuariosFiltro.setDataNasc(dataNasc);
        usuariosFiltro.setDataCadast(dataCadast);
        usuariosFiltro.setPerfil(perfil);
        usuariosFiltro.setEmailUsuario(emailUsuario);
        usuariosFiltro.setCpf(cpf);
        List<Usuario> usuarios = this.service.buscar(usuariosFiltro);
        return ResponseEntity.ok(usuarios);
    }
}
