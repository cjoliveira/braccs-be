package braccs.cattlecontrol.controller;

import braccs.cattlecontrol.dto.AnimalDTO;
import braccs.cattlecontrol.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/animais")
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class AnimaisController {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionarAnimal(@RequestBody AnimalDTO dto) {
        try {
            return ResponseEntity.ok(animalService.salvar(dto));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/atualizar/{id_animal}")
    public ResponseEntity<Object> atualizarAnimal(@PathVariable("id_animal") Long idAnimal, @RequestBody AnimalDTO dto) {
        try {
            return ResponseEntity.ok(animalService.atualizar(idAnimal, dto));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id_animal}")
    public ResponseEntity<Object> deletarAnimal(@PathVariable("id_animal") Long idAnimal) {
        try {
            animalService.deletar(idAnimal);
            return ResponseEntity.ok().build();
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/buscar/{id_animal}")
    public ResponseEntity<Object> buscarAnimalPorId(@PathVariable("id_animal") Long idAnimal) {
        try {
            return ResponseEntity.ok(animalService.buscar(idAnimal));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> buscarTodosAnimais() {
        try {
            return ResponseEntity.ok(animalService.buscarAnimais());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/por-filtros")
    public ResponseEntity<Object> buscarPorFiltros(
            @RequestParam(value = "id_mae", required = false) Long idMae,
            @RequestParam(value = "id_usuario", required = false) Long idUsuario,
            @RequestParam(value = "num_id", required = false) String numId,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "data_nasc", required = false) LocalDateTime dataNasc,
            @RequestParam(value = "peso", required = false) Long peso,
            @RequestParam(value = "status_atual", required = false) String statusAtual,
            @RequestParam(value = "preco", required = false) Long preco,
            @RequestParam(value = "data_cadastro", required = false) LocalDateTime dataCadastro) {
        try {
            return ResponseEntity.ok(animalService.buscarAnimaisPorFiltro(idMae, idUsuario, numId, tipo, dataNasc, peso, statusAtual, preco, dataCadastro));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
