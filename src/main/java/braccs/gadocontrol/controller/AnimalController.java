package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.AnimalDTO;
import braccs.gadocontrol.model.entity.Animal;
import braccs.gadocontrol.model.entity.Usuario;
import braccs.gadocontrol.service.AnimalService;
import braccs.gadocontrol.service.UsuarioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/gado/animal")
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class AnimalController {
    private final AnimalService animalService;
    private final UsuarioService usuarioService;

    public AnimalController(AnimalService animalService, UsuarioService usuarioService) {
        this.animalService = animalService;
        this.usuarioService = usuarioService;
    }

    private Animal converter(AnimalDTO dto) {
        Animal animal = new Animal();
        animal.setNumId(dto.getNumId());
        animal.setTipo(dto.getTipo());
        animal.setDataNasc(dto.getDataNasc());
        animal.setPeso(dto.getPeso());
        animal.setStatusAtual(dto.getStatusAtual());
        animal.setPreco(dto.getPreco());
        animal.setDataCadastro(dto.getDataCadastro());
        animal.setGenero(dto.getGenero());
        animal.setNumCompra(dto.getNumCompra());

        if (dto.getIdMae() != null) {
            animal.setMae(animalService.consultarPorId(dto.getIdMae()).orElse(null));
        }

        if (dto.getIdUsuarioCadastro() != null) {
            animal.setUsuarioCadastro(usuarioService.consultarPorId(dto.getIdUsuarioCadastro()).orElse(null));
        }

        return animal;
    }

    @PostMapping("/salvar-animal")
    public ResponseEntity salvar(@RequestBody AnimalDTO dto) {
        if (dto.getIdMae() != null && animalService.consultarPorId(dto.getIdMae()).isEmpty()) {
            return ResponseEntity.badRequest().body("ID da mãe não foi encontrado na base de dados!");
        } else if (dto.getIdUsuarioCadastro() != null && usuarioService.consultarPorId(dto.getIdUsuarioCadastro()).isEmpty()) {
            return ResponseEntity.badRequest().body("ID do usuário de cadastro não foi encontrado na base de dados!");
        }

        Animal animal = converter(dto);
        try {
            animal = animalService.salvar(animal);
            return ResponseEntity.ok(animal);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar-animal/{idAnimal}")
    public ResponseEntity deletar(@PathVariable("idAnimal") Long idAnimal) {
        return animalService.consultarPorId(idAnimal)
                .map(animal -> {
                    animalService.deletar(animal);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.badRequest().body("O id do animal informado não foi encontrado na base de dados, portanto não pode ser excluído."));
    }

    @PutMapping("/atualizar-animal/{idAnimal}")
    public ResponseEntity atualizar(@PathVariable("idAnimal") Long idAnimal, @RequestBody AnimalDTO dto) {
        return animalService.consultarPorId(idAnimal)
                .map(animalExistente -> {
                    if (dto.getIdMae() != null && animalService.consultarPorId(dto.getIdMae()).isEmpty()) {
                        return ResponseEntity.badRequest().body("ID da mãe não foi encontrado na base de dados!");
                    } else if (dto.getIdUsuarioCadastro() != null && usuarioService.consultarPorId(dto.getIdUsuarioCadastro()).isEmpty()) {
                        return ResponseEntity.badRequest().body("ID do usuário de cadastro não foi encontrado na base de dados!");
                    }

                    Animal animalAtualizado = converter(dto);
                    animalAtualizado.setIdAnimal(animalExistente.getIdAnimal());
                    animalService.atualizar(animalAtualizado);
                    return ResponseEntity.ok(animalAtualizado);
                })
                .orElse(ResponseEntity.badRequest().body("O id do animal informado não foi encontrado na base de dados."));
    }

    @GetMapping("/buscar-animais")
    public ResponseEntity<List<Animal>> buscarAnimais(
            @RequestParam(value = "idAnimal", required = false) Long idAnimal,
            @RequestParam(value = "idMae", required = false) Long idMae,
            @RequestParam(value = "idUsuarioCadastro", required = false) Long idUsuarioCadastro,
            @RequestParam(value = "numId", required = false) String numId,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "dataNasc", required = false) Date dataNasc,
            @RequestParam(value = "peso", required = false) Long peso,
            @RequestParam(value = "statusAtual", required = false) String statusAtual,
            @RequestParam(value = "preco", required = false) Long preco,
            @RequestParam(value = "dataCadastro", required = false) Date dataCadastro,
            @RequestParam(value = "genero", required = false) String genero,
            @RequestParam(value = "numCompra", required = false) Long numCompra) {

        Animal animalFiltro = new Animal();
        animalFiltro.setIdAnimal(idAnimal);
        animalFiltro.setNumId(numId);
        animalFiltro.setTipo(tipo);
        animalFiltro.setDataNasc(dataNasc);
        animalFiltro.setPeso(peso);
        animalFiltro.setStatusAtual(statusAtual);
        animalFiltro.setPreco(preco);
        animalFiltro.setDataCadastro(dataCadastro);
        animalFiltro.setGenero(genero);
        animalFiltro.setNumCompra(numCompra);

        if (idUsuarioCadastro != null) {
            Usuario usuarioCadastro = new Usuario();
            usuarioCadastro.setIdUsuario(idUsuarioCadastro);
            animalFiltro.setUsuarioCadastro(usuarioCadastro);
        }

        if (idMae != null) {
            Animal mae = new Animal();
            mae.setIdAnimal(idMae);
            animalFiltro.setMae(mae);
        }

        List<Animal> animais = animalService.buscar(animalFiltro);
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/dados-rebanho")
    public ResponseEntity<List<Animal>> fetchDadosRebanho(
            @RequestParam(value="data_inicio", required = true) String dataInicio,
            @RequestParam(value = "data_fim", required = true) String dataFim) {

        List<Animal> animais = animalService.buscarAnimaisDisponiveisPorData(dataInicio, dataFim);
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/dados-rebanho-csv")
    public ResponseEntity<String> fetchDadosRebanhoCsv(
            @RequestParam(value="data_inicio", required = true) String dataInicio,
            @RequestParam(value = "data_fim", required = true) String dataFim) {

        String csvData = animalService.getAnimaisAsCsv(dataInicio, dataFim);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = LocalDate.now().format(dtf);
        String filename = "dados_rebanho_" + currentDate + ".csv";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv; charset=utf-8");

        return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
    }
}