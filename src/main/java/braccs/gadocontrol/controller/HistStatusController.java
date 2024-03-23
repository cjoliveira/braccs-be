package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.HistStatusDTO;
import braccs.gadocontrol.model.entity.Animal;
import braccs.gadocontrol.model.entity.HistStatus;
import braccs.gadocontrol.service.AnimalService;
import braccs.gadocontrol.service.HistStatusService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/gado/historicoStatus"})
@CrossOrigin(
        origins = {"http://127.0.0.1:3000"}
)
public class HistStatusController {
    HistStatusService service;
    AnimalService animalService;

    public HistStatusController(HistStatusService service, AnimalService animalService) {
        this.service = service;
        this.animalService = animalService;
    }

    private HistStatus converter(HistStatusDTO dto) {
        HistStatus status = new HistStatus();
        status.setStatusAnimal(dto.getStatusAnimal());
        if (dto.getDataStatus() != null) {
            status.setDataStatus(dto.getDataStatus());
        }

        if (dto.getIdAnimal() != null && this.animalService.consultarPorId(dto.getIdAnimal()).isPresent()) {
            status.setAnimal((Animal)this.animalService.consultarPorId(dto.getIdAnimal()).get());
        }

        return status;
    }

    @PostMapping({"/salvar-status"})
    public ResponseEntity salvar(@RequestBody HistStatusDTO dto) {
        HistStatus status = this.converter(dto);
        if (dto.getIdAnimal() != null && this.animalService.consultarPorId(dto.getIdAnimal()).isEmpty()) {
            return ResponseEntity.badRequest().body("O id do animal não existe!");
        } else if (dto.getDataStatus() == null) {
            return ResponseEntity.badRequest().body("Data inválida");
        } else if (this.service.consultarPorId(dto.getIdAnimal(), dto.getDataStatus()).isPresent()) {
            return ResponseEntity.badRequest().body("O id já utilizado pelo BD!");
        } else {
            try {
                status = this.service.salvar(status);
                return status == null ? ResponseEntity.badRequest().body("id já utilizado pelo BD") : ResponseEntity.ok(status);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }
    }

    @PutMapping({"/atualizar-status/{IDANIMAL}/{DATASTATUS}"})
    public ResponseEntity atualizar(@PathVariable("IDANIMAL") Long idAnimal, @PathVariable("DATASTATUS") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStatus, @RequestBody HistStatusDTO dto) {
        return (ResponseEntity)this.service.consultarPorId(idAnimal, dataStatus).map((entity) -> {
            try {
                HistStatus status = this.converter(dto);
                status.setAnimal(entity.getAnimal());
                status.setDataStatus(entity.getDataStatus());
                this.service.atualizar(status);
                return ResponseEntity.ok(status);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do status informado não foi encontrado na base de dados");
        });
    }

    @DeleteMapping({"/deletar-status/{IDANIMAL}/{DATASTATUS}"})
    public ResponseEntity deletar(@PathVariable("IDANIMAL") Long idAnimal, @PathVariable("DATASTATUS") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStatus) {
        return (ResponseEntity)this.service.consultarPorId(idAnimal, dataStatus).map((entity) -> {
            this.service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do Status informado não foi encontrado na base de dados, por isso não pode ser excluído.");
        });
    }

    @GetMapping({"/buscar-status"})
    public ResponseEntity buscar(@RequestParam(value = "STATUSANIMAL",required = false) String statusAnimal) {
        HistStatus statusFiltro = new HistStatus();
        statusFiltro.setStatusAnimal(statusAnimal);
        List<HistStatus> status = this.service.buscar(statusFiltro);
        return ResponseEntity.ok(status);
    }
}
