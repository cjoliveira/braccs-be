package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.ProtocoloDTO;
import braccs.gadocontrol.model.entity.CicloIATF;
import braccs.gadocontrol.model.entity.Protocolo;
import braccs.gadocontrol.service.CicloIATFService;
import braccs.gadocontrol.service.ProtocoloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/gado/protocolo"})
@CrossOrigin(
        origins = {"http://127.0.0.1:3000"}
)
public class ProtocoloController {
    ProtocoloService service;
    CicloIATFService serviceCiclo;

    public ProtocoloController(ProtocoloService service, CicloIATFService serviceCiclo) {
        this.service = service;
        this.serviceCiclo = serviceCiclo;
    }

    private Protocolo converter(ProtocoloDTO dto) {
        Protocolo protocolo = new Protocolo();
        protocolo.setDataInicio(dto.getDataInicio());
        protocolo.setDataFim(dto.getDataFim());
        protocolo.setStatusProtocolo(dto.getStatusProtocolo());
        if (dto.getIdProtocolo() != null) {
            protocolo.setIdProtocolo(dto.getIdProtocolo());
        }

        if (dto.getIdCiclo() != null && this.serviceCiclo.consultarPorId(dto.getIdCiclo()).isPresent()) {
            protocolo.setCiclo((CicloIATF)this.serviceCiclo.consultarPorId(dto.getIdCiclo()).get());
        }

        return protocolo;
    }

    @PostMapping({"/salvar-protocolo"})
    public ResponseEntity salvar(@RequestBody ProtocoloDTO dto) {
        Protocolo protocolo = this.converter(dto);
        if (dto.getIdProtocolo() != null && dto.getIdCiclo() != null && this.service.consultarPorId(dto.getIdProtocolo(), dto.getIdCiclo()).isPresent()) {
            return ResponseEntity.badRequest().body("id já é utilizado pelo BD");
        } else if (dto.getIdCiclo() != null && this.serviceCiclo.consultarPorId(dto.getIdCiclo()).isEmpty()) {
            return ResponseEntity.badRequest().body("O id do ciclo não econtrado!");
        } else if (dto.getIdCiclo() != null && dto.getIdProtocolo() != null) {
            try {
                protocolo = this.service.salvar(protocolo);
                return ResponseEntity.ok(protocolo);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Chave primária composta inválida (null)");
        }
    }

    @PutMapping({"/atualizar-protocolo/{IDPROTOCOLO}/{IDCICLO}"})
    public ResponseEntity atualizar(@PathVariable("IDPROTOCOLO") long idProtocolo, @PathVariable("IDCICLO") long idCiclo, @RequestBody ProtocoloDTO dto) {
        return (ResponseEntity)this.service.consultarPorId(idProtocolo, idCiclo).map((entity) -> {
            try {
                Protocolo protocolo = this.converter(dto);
                protocolo.setIdProtocolo(entity.getIdProtocolo());
                protocolo.setCiclo(entity.getCiclo());
                this.service.atualizar(protocolo);
                return ResponseEntity.ok(protocolo);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do protocolo informado não foi encontrado na base de dados");
        });
    }

    @DeleteMapping({"/deletar-protocolo/{IDPROTOCOLO}/{IDCICLO}"})
    public ResponseEntity deletar(@PathVariable("IDPROTOCOLO") long idProtocolo, @PathVariable("IDCICLO") long idCiclo) {
        return (ResponseEntity)this.service.consultarPorId(idProtocolo, idCiclo).map((entity) -> {
            this.service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do protocolo informado não foi encontrado na base de dados, por isso não pode ser excluído.");
        });
    }

    @GetMapping({"/buscar-protocolos"})
    public ResponseEntity buscar(@RequestParam(value = "DATAINICIO",required = false) Date dataInicio, @RequestParam(value = "DATAFIM",required = false) Date dataFim, @RequestParam(value = "STATUSPROTOCOLO",required = false) String statusProtocolo) {
        Protocolo protocoloFiltro = new Protocolo();
        protocoloFiltro.setDataInicio(dataInicio);
        protocoloFiltro.setDataFim(dataFim);
        protocoloFiltro.setStatusProtocolo(statusProtocolo);
        List<Protocolo> protocolos = this.service.buscar(protocoloFiltro);
        return ResponseEntity.ok(protocolos);
    }
}
