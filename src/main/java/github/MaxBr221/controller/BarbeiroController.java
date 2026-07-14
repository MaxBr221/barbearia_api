package github.MaxBr221.controller;

import github.MaxBr221.dtos.barbeiro.BarbeiroRequestDTO;
import github.MaxBr221.dtos.barbeiro.BarbeiroResponseDTO;
import github.MaxBr221.service.BarbeiroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiro")
@Slf4j
@RequiredArgsConstructor
public class BarbeiroController {
    private BarbeiroService barbeiroService;

    @PostMapping
    public ResponseEntity<BarbeiroResponseDTO> create(@RequestBody BarbeiroRequestDTO barbeiroRequestDTO){
        BarbeiroResponseDTO barbeiro = barbeiroService.create(barbeiroRequestDTO);
        log.info("Salvando barbeiro {}", barbeiro.nome());
        return ResponseEntity.status(HttpStatus.CREATED).body(barbeiro);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        barbeiroService.delete(id);
        log.info("Barbeiro apagado com sucesso!");
        return ResponseEntity.noContent().build();

    }
    @PutMapping
    public ResponseEntity<BarbeiroResponseDTO> update(@PathVariable Long id, @RequestBody BarbeiroRequestDTO barbeiroRequestDTO){
        BarbeiroResponseDTO barbeiroUpdate = barbeiroService.update(id, barbeiroRequestDTO);
        log.info("Barbeiro de id {} editado com sucesso!", barbeiroUpdate.id());
        return ResponseEntity.ok(barbeiroUpdate);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroResponseDTO> findById(@PathVariable Long id){
        BarbeiroResponseDTO barbeiroBuscado = barbeiroService.findById(id);
        log.info("Barbeiro {} buscado com sucesso!", barbeiroBuscado.nome());
        return ResponseEntity.ok(barbeiroBuscado);
    }
    @GetMapping
    public ResponseEntity<List<BarbeiroResponseDTO>> findAll(){
        List<BarbeiroResponseDTO> listaBarbeiro = barbeiroService.findAll();
        log.info("Buscando todos os barbeiros!");
        return ResponseEntity.ok(listaBarbeiro);

    }
}
