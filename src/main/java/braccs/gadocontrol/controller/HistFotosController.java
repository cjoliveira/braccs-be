package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.HistFotosDTO;
import braccs.gadocontrol.model.entity.Animal;
import braccs.gadocontrol.model.entity.HistFotos;
import braccs.gadocontrol.service.AnimalService;
import braccs.gadocontrol.service.HistFotosService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/gado/historicoFotos"})
@CrossOrigin(
        origins = {"http://127.0.0.1:3000"}
)
public class HistFotosController {
    HistFotosService service;
    AnimalService animalService;

    public HistFotosController(HistFotosService service, AnimalService animalService) {
        this.service = service;
        this.animalService = animalService;
    }

    private HistFotos converter(HistFotosDTO dto) {
        HistFotos foto = new HistFotos();
        foto.setFoto(dto.getFoto());
        if (dto.getDataFoto() != null) {
            foto.setDataFoto(dto.getDataFoto());
        }

        if (dto.getIdAnimal() != null && this.animalService.consultarPorId(dto.getIdAnimal()).isPresent()) {
            foto.setAnimal((Animal)this.animalService.consultarPorId(dto.getIdAnimal()).get());
        }

        return foto;
    }

    @PostMapping({"/salvar-foto"})
    public ResponseEntity salvar(@RequestBody HistFotosDTO dto) {
        HistFotos foto = this.converter(dto);
        if (dto.getIdAnimal() != null && this.animalService.consultarPorId(dto.getIdAnimal()).isEmpty()) {
            return ResponseEntity.badRequest().body("O id do animal não foi localizado");
        } else if (this.service.consultarPorId(dto.getIdAnimal(), dto.getDataFoto()).isPresent()) {
            return ResponseEntity.badRequest().body("O id já utilizado pelo BD");
        } else {
            try {
                foto = this.service.salvar(foto);
                return ResponseEntity.ok(foto);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }
    }

    @PutMapping({"/atualizar-foto/{IDANIMAL}/{DATAFOTO}"})
    public ResponseEntity atualizar(@PathVariable("IDANIMAL") Long idAnimal, @PathVariable("DATAFOTO") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFoto, @RequestBody HistFotosDTO dto) {
        return (ResponseEntity)this.service.consultarPorId(idAnimal, dataFoto).map((entity) -> {
            try {
                HistFotos foto = this.converter(dto);
                foto.setAnimal(entity.getAnimal());
                foto.setDataFoto(entity.getDataFoto());
                this.service.atualizar(foto);
                return ResponseEntity.ok(foto);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id da foto informado não foi encontrado na base de dados");
        });
    }

    @DeleteMapping({"/deletar-foto/{IDANIMAL}/{DATAFOTO}"})
    public ResponseEntity deletar(@PathVariable("IDANIMAL") Long idAnimal, @PathVariable("DATAFOTO") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFoto) {
        return (ResponseEntity)this.service.consultarPorId(idAnimal, dataFoto).map((entity) -> {
            this.service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id da foto informado não foi encontrado na base de dados, por isso não pode ser excluído.");
        });
    }

    @GetMapping({"/buscar-fotos"})
    public ResponseEntity buscar(@RequestParam(value = "FOTO",required = false) byte[] foto) {
        HistFotos fotoFiltro = new HistFotos();
        fotoFiltro.setFoto(foto);
        List<HistFotos> fotos = this.service.buscar(fotoFiltro);
        return ResponseEntity.ok(fotos);
    }
}

