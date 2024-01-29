package braccs.cattlecontrol.controller;

import braccs.cattlecontrol.dto.UsuarioDTO;
import braccs.cattlecontrol.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
        return null;
    }

    @DeleteMapping("/deletar/{id_usuario}")
    public ResponseEntity deletar(@PathVariable("id_usuario") long idUsuario) {
        return null;
    }

    @PutMapping("/atualizar/{id_usuario}")
    public ResponseEntity atualizar(@PathVariable("id_usuario") long idUsuario, @RequestBody UsuarioDTO dto) {
        return null;
    }


    @GetMapping("/buscar")
    public ResponseEntity buscar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "LOGIN", required = false) String logIn,
            @RequestParam(value = "SENHA", required = false) String senha,
            @RequestParam(value = "DATANASC", required = false) Date dataNasc,
            @RequestParam(value = "DATACADAST", required = false) Date dataCadast,
            @RequestParam(value = "PERFIL", required = false) String perfil,
            @RequestParam(value = "EMAIL", required = false) String emailUsuario,
            @RequestParam(value = "CPF", required = false) String cpf) {

        return null;
    }
}
