package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.VendaDTO;
import braccs.gadocontrol.model.entity.Venda;
import braccs.gadocontrol.service.AnimalService;
import braccs.gadocontrol.service.ClienteService;
import braccs.gadocontrol.service.UsuarioService;
import braccs.gadocontrol.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/gado/venda"})
@CrossOrigin(
        origins = {"http://127.0.0.1:3000"}
)
public class VendaController {
    VendaService service;
    AnimalService serviceAnimal;
    UsuarioService serviceUsuario;
    ClienteService serviceCliente;

    public VendaController(VendaService service, AnimalService serviceAnimal, UsuarioService serviceUsuario, ClienteService serviceCliente) {
        this.service = service;
        this.serviceAnimal = serviceAnimal;
        this.serviceUsuario = serviceUsuario;
        this.serviceCliente = serviceCliente;
    }

    private Venda converter(VendaDTO dto) {
        Venda venda = new Venda();
        venda.setPreco(dto.getPreco());
        venda.setDataVenda(dto.getDataVenda());
        venda.setNumVenda(dto.getNumVenda());
        if (dto.getIdAnimal() != null && this.serviceAnimal.consultarPorId(dto.getIdAnimal()).isPresent()) {
            venda.setAnimal(this.serviceAnimal.consultarPorId(dto.getIdAnimal()).get());
        }

        if (dto.getIdUsuario() != null && this.serviceUsuario.consultarPorId(dto.getIdUsuario()).isPresent()) {
            venda.setUsuario(this.serviceUsuario.consultarPorId(dto.getIdUsuario()).get());
        }

        if (dto.getIdCliente() != null && this.serviceCliente.consultarPorId(dto.getIdCliente()).isPresent()) {
            venda.setCliente(this.serviceCliente.consultarPorId(dto.getIdCliente()).get());
        }

        return venda;
    }

    @PostMapping({"/salvar-venda"})
    public ResponseEntity salvar(@RequestBody VendaDTO dto) {
        Venda venda = this.converter(dto);
        if (this.service.consultarPorId(dto.getIdAnimal(), dto.getIdUsuario(), dto.getIdCliente()).isPresent()) {
            return ResponseEntity.badRequest().body("ID da venda já existe na BD");
        } else if (this.serviceAnimal.consultarPorId(dto.getIdAnimal()).isEmpty()) {
            return ResponseEntity.badRequest().body("ID do animal não existe na BD");
        } else if (this.serviceCliente.consultarPorId(dto.getIdCliente()).isEmpty()) {
            return ResponseEntity.badRequest().body("ID do cliente não existe na BD");
        } else if (this.serviceUsuario.consultarPorId(dto.getIdUsuario()).isEmpty()) {
            return ResponseEntity.badRequest().body("ID do usuario não existe na BD");
        } else {
            try {
                venda = this.service.salvar(venda);
                this.serviceAnimal.consultarPorId(dto.getIdAnimal()).get().setStatusAtual("VENDIDO");
                return ResponseEntity.ok(venda);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }
    }

    @PutMapping({"/atualizar-venda/{IDANIMAL}/{IDUSUARIO}/{IDCLIENTE}"})
    public ResponseEntity atualizar(@PathVariable("IDANIMAL") long idAnimal, @PathVariable("IDUSUARIO") Long idUsuario, @PathVariable("IDCLIENTE") Long idCliente, @RequestBody VendaDTO dto) {
        return this.service.consultarPorId(idAnimal, idUsuario, idCliente).map((entity) -> {
            try {
                Venda venda = this.converter(dto);
                venda.setAnimal(entity.getAnimal());
                venda.setCliente(entity.getCliente());
                venda.setUsuario(entity.getUsuario());
                this.service.atualizar(venda);
                return ResponseEntity.ok(venda);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do cliente informado não foi encontrado na base de dados");
        });
    }

    @DeleteMapping({"/deletar-venda/{IDANIMAL}/{IDUSUARIO}/{IDCLIENTE}"})
    public ResponseEntity deletar(@PathVariable("IDANIMAL") Long idAnimal, @PathVariable("IDUSUARIO") Long idUsuario, @PathVariable("IDCLIENTE") Long idCliente) {
        return (ResponseEntity) this.service.consultarPorId(idAnimal, idUsuario, idCliente).map((entity) -> {
            this.service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id da Venda informado não foi encontrado na base de dados, por isso não pode ser excluído.");
        });
    }

    @GetMapping({"/buscar-vendas"})
    public ResponseEntity buscar(@RequestParam(value = "PRECO", required = false) Double preco, @RequestParam(value = "DATAVENDA", required = false) Date dataVenda, @RequestParam(value = "NUMVENDA", required = false) Long numVenda) {
        Venda vendaFiltro = new Venda();
        vendaFiltro.setDataVenda(dataVenda);
        vendaFiltro.setPreco(preco);
        vendaFiltro.setNumVenda(numVenda);
        List<Venda> venda = this.service.buscar(vendaFiltro);
        return ResponseEntity.ok(venda);
    }
}
