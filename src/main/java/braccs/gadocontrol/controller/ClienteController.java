package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.ClienteDTO;
import braccs.gadocontrol.model.entity.Cliente;
import braccs.gadocontrol.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/gado/cliente"})
@CrossOrigin(
        origins = {"http://127.0.0.1:3000"}
)
public class ClienteController {
    ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    private Cliente converter(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmailCliente(dto.getEmailCliente());
        cliente.setCpfCnpj(dto.getCpfCnpj());
        cliente.setTelefone(dto.getTelefone());
        if (dto.getIdCliente() != null) {
            cliente.setIdCliente(dto.getIdCliente());
        }

        return cliente;
    }

    @PostMapping({"/salvar-cliente"})
    public ResponseEntity salvar(@RequestBody ClienteDTO dto) {
        try {
            Cliente cliente = this.converter(dto);
            cliente = this.service.salvar(cliente);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException var3) {
            return ResponseEntity.badRequest().body(var3.getMessage());
        }
    }

    @DeleteMapping({"/deletar-cliente/{IDCLIENTE}"})
    public ResponseEntity deletar(@PathVariable("IDCLIENTE") long idCliente) {
        return (ResponseEntity)this.service.consultarPorId(idCliente).map((entity) -> {
            this.service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do cliente informado não foi encontrado na base de dados, por isso não pode ser excluído.");
        });
    }

    @PutMapping({"/atualizar-cliente/{IDCLIENTE}"})
    public ResponseEntity atualizar(@PathVariable("IDCLIENTE") long idCliente, @RequestBody ClienteDTO dto) {
        return (ResponseEntity)this.service.consultarPorId(idCliente).map((entity) -> {
            try {
                Cliente cliente = this.converter(dto);
                cliente.setIdCliente(entity.getIdCliente());
                this.service.atualizar(cliente);
                return ResponseEntity.ok(cliente);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do cliente informado não foi encontrado na base de dados");
        });
    }

    @GetMapping({"/buscar-clientes"})
    public ResponseEntity buscar(@RequestParam(value = "NOME",required = false) String nome, @RequestParam(value = "EMAIL",required = false) String email, @RequestParam(value = "CPFCNPJ",required = false) String cpfCnpj, @RequestParam(value = "TELEFONE",required = false) String telefone) {
        Cliente clienteFiltro = new Cliente();
        clienteFiltro.setNome(nome);
        clienteFiltro.setEmailCliente(email);
        clienteFiltro.setCpfCnpj(cpfCnpj);
        clienteFiltro.setTelefone(telefone);
        List<Cliente> clientes = this.service.buscar(clienteFiltro);
        return ResponseEntity.ok(clientes);
    }
}
