package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.CicloIATFDTO;
import braccs.gadocontrol.model.entity.CicloIATF;
import braccs.gadocontrol.service.CicloIATFService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/gado/ciclo"})
@CrossOrigin(
        origins = {"http://127.0.0.1:3000"}
)
public class CicloIATFController {
    CicloIATFService service;

    public CicloIATFController(CicloIATFService service) {
        this.service = service;
    }

    private CicloIATF converter(CicloIATFDTO dto) {
        CicloIATF ciclo = new CicloIATF();
        ciclo.setDataInicio(dto.getDataInicio());
        ciclo.setDataFim(dto.getDataFim());
        ciclo.setStatusCiclo(dto.getStatusCiclo());
        ciclo.setDg(dto.getDg());
        if (dto.getIdCiclo() != null) {
            ciclo.setIdCiclo(dto.getIdCiclo());
        }

        return ciclo;
    }

    @PostMapping({"/salvar-ciclo"})
    public ResponseEntity salvar(@RequestBody CicloIATFDTO dto) {
        try {
            CicloIATF ciclo = this.converter(dto);
            ciclo = this.service.salvar(ciclo);
            return ResponseEntity.ok(ciclo);
        } catch (RuntimeException var3) {
            return ResponseEntity.badRequest().body(var3.getMessage());
        }
    }

    @DeleteMapping({"/deletar-ciclo/{IDCICLO}"})
    public ResponseEntity deletar(@PathVariable("IDCICLO") long idCiclo) {
        return (ResponseEntity)this.service.consultarPorId(idCiclo).map((entity) -> {
            this.service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do cicloIATF informado não foi encontrado na base de dados, por isso não pode ser excluído.");
        });
    }

    @PutMapping({"/atualizar-ciclo/{IDCICLO}"})
    public ResponseEntity atualizar(@PathVariable("IDCICLO") long idCiclo, @RequestBody CicloIATFDTO dto) {
        return (ResponseEntity)this.service.consultarPorId(idCiclo).map((entity) -> {
            try {
                CicloIATF ciclo = this.converter(dto);
                ciclo.setIdCiclo(entity.getIdCiclo());
                this.service.atualizar(ciclo);
                return ResponseEntity.ok(ciclo);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do cicloIATF informado não foi encontrado na base de dados");
        });
    }

    @GetMapping({"/buscar-ciclos"})
    public ResponseEntity buscar(@RequestParam(value = "DATAINICIO",required = false) Date dataInicio, @RequestParam(value = "DATAFIM",required = false) Date dataFim, @RequestParam(value = "STATUSCICLO",required = false) String statusCiclo, @RequestParam(value = "DG",required = false) Long dg) {
        CicloIATF cicloFiltro = new CicloIATF();
        cicloFiltro.setDataInicio(dataInicio);
        cicloFiltro.setDataFim(dataFim);
        cicloFiltro.setStatusCiclo(statusCiclo);
        cicloFiltro.setDg(dg);
        List<CicloIATF> ciclos = this.service.buscar(cicloFiltro);
        return ResponseEntity.ok(ciclos);
    }
}
