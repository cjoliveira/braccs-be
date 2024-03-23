package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.UsuarioDTO;
import braccs.gadocontrol.model.entity.Usuario;
import braccs.gadocontrol.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/gado/usuario"})
@CrossOrigin(
        origins = {"http://127.0.0.1:3000"}
)
public class UsuarioController {
    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
        Optional<Usuario> optionalUsuario = usuarioService.consultarPorLogin(login);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            if(usuario.getSenha().equals(senha)){
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.badRequest().body("Senha incorreta!");
            }
        } else {
            return ResponseEntity.badRequest().body("Usuário não encontrado!");
        }
    }

    @PostMapping({"/salvar-usuario"})
    public ResponseEntity salvar(@RequestParam("login") String login, @RequestParam("senha") String senha, @RequestBody UsuarioDTO dto) {
        Optional<Usuario> existingUser = usuarioService.consultarPorLogin(login);
        if(existingUser.isPresent()){
            return ResponseEntity.badRequest().body("O login informado já está cadastrado na base de dados!");
        }
        Usuario usuario = dto.converterParaUsuarioComSenha(login, senha);
        usuario = this.usuarioService.salvar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping({"/deletar-usuario/{idUsuario}"})
    public ResponseEntity<?> deletar(@PathVariable("idUsuario") long idUsuario) {
        Optional<Usuario> usuario = this.usuarioService.consultarPorId(idUsuario);
        if(usuario.isPresent()){
            this.usuarioService.deletar(usuario.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.badRequest().body("O id do usuário informado não foi encontrado na base de dados, por isso não pode ser excluído.");
        }
    }

    @PutMapping({"/atualizar-usuario/{idUsuario}"})
    public ResponseEntity atualizar(@PathVariable("idUsuario") long idUsuario, @RequestBody UsuarioDTO dto) {
        Optional<Usuario> optionalEntity = this.usuarioService.consultarPorId(idUsuario);
        if(optionalEntity.isPresent()){
            Usuario entity = optionalEntity.get();
            Usuario usuario = dto.converterParaUsuario();
            usuario.setIdUsuario(entity.getIdUsuario());
            usuario.setLogIn(entity.getLogIn());
            usuario.setSenha(entity.getSenha());
            this.usuarioService.atualizar(usuario);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.badRequest().body("O id do usuário informado não foi encontrado na base de dados");
        }
    }

    @GetMapping({"/buscar-usuarios"})
    public ResponseEntity buscar(@RequestParam(value = "nome",required = false) String nome, @RequestParam(value = "login",required = false) String logIn, @RequestParam(value = "data_nasc",required = false) Date dataNasc, @RequestParam(value = "data_cadast",required = false) Date dataCadast, @RequestParam(value = "perfil",required = false) String perfil, @RequestParam(value = "email",required = false) String emailUsuario, @RequestParam(value = "cpf",required = false) String cpf) {
        Usuario usuarioFiltro = new Usuario();
        usuarioFiltro.setNome(nome);
        usuarioFiltro.setLogIn(logIn);
        usuarioFiltro.setDataNasc(dataNasc);
        usuarioFiltro.setDataCadast(dataCadast);
        usuarioFiltro.setPerfil(perfil);
        usuarioFiltro.setEmailUsuario(emailUsuario);
        usuarioFiltro.setCpf(cpf);
        List<Usuario> usuarios = this.usuarioService.buscar(usuarioFiltro);
        return ResponseEntity.ok(usuarios);
    }
}
