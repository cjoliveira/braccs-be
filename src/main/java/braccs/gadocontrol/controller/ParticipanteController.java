package braccs.gadocontrol.controller;

import braccs.gadocontrol.dto.ParticipanteDTO;
import braccs.gadocontrol.keys.ProtocoloKey;
import braccs.gadocontrol.model.entity.Animal;
import braccs.gadocontrol.model.entity.Participante;
import braccs.gadocontrol.model.entity.Protocolo;
import braccs.gadocontrol.service.AnimalService;
import braccs.gadocontrol.service.CicloIATFService;
import braccs.gadocontrol.service.ParticipanteService;
import braccs.gadocontrol.service.ProtocoloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/gado/participante"})
@CrossOrigin(
        origins = {"http://127.0.0.1:3000"}
)
public class ParticipanteController {
    ParticipanteService service;
    AnimalService serviceAnimal;
    ProtocoloService serviceProtocolo;
    CicloIATFService serviceCiclo;

    public ParticipanteController(ParticipanteService service, AnimalService serviceAnimal, ProtocoloService serviceProtocolo, CicloIATFService serviceCiclo) {
        this.service = service;
        this.serviceAnimal = serviceAnimal;
        this.serviceProtocolo = serviceProtocolo;
        this.serviceCiclo = serviceCiclo;
    }

    private Participante converter(ParticipanteDTO dto) {
        Participante participante = new Participante();
        participante.setAplicouHormonio(dto.getAplicouHormonio());
        participante.setSastusFecundacao(dto.getStatusFecundacao());
        if (dto.getIdProtocolo() != null && dto.getIdCiclo() != null && this.serviceProtocolo.consultarPorId(dto.getIdProtocolo(), dto.getIdCiclo()).isPresent()) {
            participante.setProtocolo((Protocolo)this.serviceProtocolo.consultarPorId(dto.getIdProtocolo(), dto.getIdCiclo()).get());
        }

        if (dto.getIdAnimal() != null && this.serviceAnimal.consultarPorId(dto.getIdAnimal()).isPresent()) {
            participante.setAnimal((Animal)this.serviceAnimal.consultarPorId(dto.getIdAnimal()).get());
        }

        return participante;
    }

    @PostMapping({"/salvar-participante"})
    public ResponseEntity salvar(@RequestBody ParticipanteDTO dto) {
        Participante participante = this.converter(dto);
        if (this.service.consultarPorId(new ProtocoloKey(dto.getIdProtocolo(), dto.getIdCiclo()), dto.getIdAnimal()).isPresent()) {
            return ResponseEntity.badRequest().body("ID do participante já existe na BD");
        } else if (this.serviceCiclo.consultarPorId(dto.getIdCiclo()).isEmpty()) {
            return ResponseEntity.badRequest().body("ID do ciclo não existe na BD");
        } else if (this.serviceProtocolo.consultarPorId(dto.getIdProtocolo(), dto.getIdCiclo()).isEmpty()) {
            return ResponseEntity.badRequest().body("ID do protocolo não existe na BD");
        } else if (this.serviceAnimal.consultarPorId(dto.getIdAnimal()).isEmpty()) {
            return ResponseEntity.badRequest().body("ID do animal não existe na BD");
        } else {
            participante = this.service.salvar(participante);
            return ResponseEntity.ok(participante);
        }
    }

    @PutMapping({"/atualizar-participante/{IDPROTOCOLO}/{IDCICLO}/{IDANIMAL}"})
    public ResponseEntity atualizar(@PathVariable("IDPROTOCOLO") long idProtocolo, @PathVariable("IDCICLO") long idCiclo, @PathVariable("IDANIMAL") Long idAnimal, @RequestBody ParticipanteDTO dto) {
        return (ResponseEntity)this.service.consultarPorId(new ProtocoloKey(idProtocolo, idCiclo), idAnimal).map((entity) -> {
            try {
                Participante participante = this.converter(dto);
                participante.setProtocolo(entity.getProtocolo());
                participante.setAnimal(entity.getAnimal());
                this.service.atualizar(participante);
                return ResponseEntity.ok(participante);
            } catch (RuntimeException var4) {
                return ResponseEntity.badRequest().body(var4.getMessage());
            }
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do participante informado não foi encontrado na base de dados");
        });
    }

    @DeleteMapping({"/deletar-participante/{IDPROTOCOLO}/{IDCICLO}/{IDANIMAL}"})
    public ResponseEntity deletar(@PathVariable("IDPROTOCOLO") Long idProtocolo, @PathVariable("IDCICLO") Long idCiclo, @PathVariable("IDANIMAL") Long idAnimal) {
        return (ResponseEntity)this.service.consultarPorId(new ProtocoloKey(idProtocolo, idCiclo), idAnimal).map((entity) -> {
            this.service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().body("O id do participante informado não foi encontrado na base de dados, por isso não pode ser excluído.");
        });
    }

    @GetMapping({"/buscar-participantes"})
    public ResponseEntity buscar(@RequestParam(value = "APLICHORMONIO",required = false) Boolean aplicouHormonio, @RequestParam(value = "STATUSFECUND",required = false) String sastusFecundacao) {
        Participante participanteFiltro = new Participante();
        participanteFiltro.setAplicouHormonio(aplicouHormonio);
        participanteFiltro.setSastusFecundacao(sastusFecundacao);
        List<Participante> participantes = this.service.buscar(participanteFiltro);
        return ResponseEntity.ok(participantes);
    }
}
