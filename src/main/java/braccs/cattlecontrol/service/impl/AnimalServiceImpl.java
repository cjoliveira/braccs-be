package braccs.cattlecontrol.service.impl;

import braccs.cattlecontrol.dto.AnimalDTO;
import braccs.cattlecontrol.model.entity.Animal;
import braccs.cattlecontrol.model.repository.AnimalRepository;
import braccs.cattlecontrol.service.AnimalService;
import braccs.cattlecontrol.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public AnimalDTO salvar(AnimalDTO animalDTO) {
        Animal animal = animalRepository.save(convertDtoToAnimal(animalDTO));
        return convertAnimalToDto(animal);
    }

    @Override
    public AnimalDTO atualizar(Long id, AnimalDTO animalDTO) {
        Optional<Animal> animalOptional = consultarPorId(id);

        if (animalOptional.isEmpty()) {
            throw new RuntimeException("Animal não encontrado");
        }

        Animal animal = animalOptional.get();
        animal.setIdMae(recuperarMae(animalDTO.getIdMae()));
        animal.setIdUsuario(usuarioService.consultarPorId(animalDTO.getIdUsuario()));
        animal.setNumId(animalDTO.getNumId());
        animal.setTipo(animalDTO.getTipo());
        animal.setDataNasc(animalDTO.getDataNasc());
        animal.setPeso(animalDTO.getPeso());
        animal.setStatusAtual(animalDTO.getStatusAtual());
        animal.setPreco(animalDTO.getPreco());
        animal.setDataCadastro(animalDTO.getDataCadastro());

        Animal animalAtualizado = animalRepository.save(animal);
        return convertAnimalToDto(animalAtualizado);
    }

    @Override
    public void deletar(Long id) {
        try {
            animalRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Animal não encontrado");
        }
    }

    @Override
    public AnimalDTO buscar(Long id) {
        Optional<Animal> animalOptional = consultarPorId(id);
        if (animalOptional.isPresent()) {
            return convertAnimalToDto(animalOptional.get());
        } else {
            throw new RuntimeException("Animal não encontrado");
        }
    }

    @Override
    public List<AnimalDTO> buscarAnimais() {
        return animalRepository.findAll().stream()
                .map(this::convertAnimalToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> buscarAnimaisPorFiltro(Long idMae, Long idUsuario, String numId, String tipo, LocalDateTime dataNasc,
                                                  Long peso, String statusAtual, Long preco, LocalDateTime dataCadastro) {

        List<Animal> animais = animalRepository.buscarPorFiltros(idMae, idUsuario, numId, tipo, dataNasc, peso,
                statusAtual, preco, dataCadastro);

        return animais.stream()
                .map(this::convertAnimalToDto)
                .collect(Collectors.toList());
    }

    private AnimalDTO convertAnimalToDto(Animal animal) {
        AnimalDTO dto = new AnimalDTO();
        dto.setIdAnimal(animal.getIdAnimal());
        dto.setIdMae(recuperarIdMae(animal.getIdMae()));
        dto.setIdUsuario(animal.getIdUsuario().getIdUsuario());
        dto.setNumId(animal.getNumId());
        dto.setTipo(animal.getTipo());
        dto.setDataNasc(animal.getDataNasc());
        dto.setPeso(animal.getPeso());
        dto.setStatusAtual(animal.getStatusAtual());
        dto.setPreco(animal.getPreco());
        dto.setDataCadastro(animal.getDataCadastro());

        return dto;
    }

    private Long recuperarIdMae(Animal animal) {
        if (animal != null) {
            return animal.getIdAnimal();
        }
        return null;
    }


    private Animal convertDtoToAnimal(AnimalDTO dto) {
        if (dto.getIdAnimal() != null) {
            throw new RuntimeException("Nao eh possivel adicionar um animal com id. Eh gerado automaticamente");
        }
        Animal animal = new Animal();
        animal.setIdMae(recuperarMae(dto.getIdMae()));
        animal.setIdUsuario(usuarioService.consultarPorId(dto.getIdUsuario()));
        animal.setNumId(dto.getNumId());
        animal.setTipo(dto.getTipo());
        animal.setDataNasc(dto.getDataNasc());
        animal.setPeso(dto.getPeso());
        animal.setStatusAtual(dto.getStatusAtual());
        animal.setPreco(dto.getPreco());
        animal.setDataCadastro(dto.getDataCadastro());

        return animal;
    }

    private Animal recuperarMae(Long idMae) {
        if (idMae != null) {
            return consultarPorId(idMae).get();
        }
        return null;
    }

    private Optional<Animal> consultarPorId(Long id) {
        return animalRepository.findById(id);
    }

}
