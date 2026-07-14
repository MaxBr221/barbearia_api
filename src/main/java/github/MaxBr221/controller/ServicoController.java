package github.MaxBr221.controller;

import github.MaxBr221.dtos.servico.ServicoRequestDTO;
import github.MaxBr221.dtos.servico.ServicoResponseDTO;
import github.MaxBr221.service.ServicoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
@Slf4j
@RequiredArgsConstructor
public class ServicoController {
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> create(@RequestBody ServicoRequestDTO servicoRequestDTO){
        ServicoResponseDTO servicoSalvo = servicoService.create(servicoRequestDTO);
        log.info("Salvando servico!");
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoSalvo);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        servicoService.delete(id);
        log.info("Servico deletado!");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idServico}")
    public ResponseEntity<ServicoResponseDTO> findById(@PathVariable Long id){
        ServicoResponseDTO servicoBuscado = servicoService.findById(id);
        log.info("Buscando servico!");
        return ResponseEntity.ok(servicoBuscado);
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> findAll(){
        List<ServicoResponseDTO> listaServico = servicoService.findAll();
        log.info("Listando servicos!");
        return ResponseEntity.ok(listaServico);
    }

    @PutMapping
    public ResponseEntity<ServicoResponseDTO> update (@PathVariable Long id,@RequestBody ServicoRequestDTO servicoRequestDTO){
        ServicoResponseDTO servicoUpdate = servicoService.update(id, servicoRequestDTO);
        log.info("Editando serviço!");
        return ResponseEntity.ok(servicoUpdate);
    }
}
