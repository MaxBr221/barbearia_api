package github.MaxBr221.controller;

import github.MaxBr221.dtos.agendamento.AgendamentoRequestDTO;
import github.MaxBr221.dtos.agendamento.AgendamentoResponseDTO;
import github.MaxBr221.dtos.usuario.ClienteFrequencia;
import github.MaxBr221.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
@Slf4j
@RequiredArgsConstructor
public class AgendamentoController {
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> create(@RequestBody AgendamentoRequestDTO agendamentoRequestDTO){
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.create(agendamentoRequestDTO);
        log.info("Salvando Agendamento do usuário {}", agendamentoRequestDTO.idUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoResponseDTO);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        agendamentoService.delete(id);
        log.info("Apagando agendamento!");
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{idAgenda}")
    public ResponseEntity<AgendamentoResponseDTO> findById(@PathVariable Long id){
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(id);
        log.info("Buscando agendamento!");
        return ResponseEntity.ok(agendamentoResponseDTO);
    }
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> findAll(){
        List<AgendamentoResponseDTO> listaAgendamentos = agendamentoService.findAll();
        log.info("Listando todos os agendamentos!");
        return ResponseEntity.ok(listaAgendamentos);
    }
    @PutMapping
    public ResponseEntity<AgendamentoResponseDTO> update(@PathVariable Long id, @RequestBody AgendamentoRequestDTO agendamentoDTO){
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.update(id, agendamentoDTO);
        log.info("Editado agendamento");
        return ResponseEntity.ok(agendamentoResponseDTO);
    }
    @GetMapping("/{hoje}")
    public ResponseEntity<List<AgendamentoResponseDTO>> agendamentosDeHoje(){
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.listarAgendamentosDeHoje();
        log.info("Listando agendamentos de hoje!");
        return ResponseEntity.ok(agendamentos);
    }
    @GetMapping("/{ativos}")
    public ResponseEntity<List<AgendamentoResponseDTO>> agendamentosAtivos(){
        List<AgendamentoResponseDTO> agendamentosAtivos = agendamentoService.agendamentosAtivos();
        log.info("Listando todos os agendamentos ativos!");
        return ResponseEntity.ok(agendamentosAtivos);
    }
    @GetMapping("/{clientes}")
    public ResponseEntity<List<ClienteFrequencia>> clientesMaisFrequentes(){
        List<ClienteFrequencia> clientesFrequentes = agendamentoService.clientesMaisFrequentes();
        log.info("Listando clientes mais frequentes");
        return ResponseEntity.ok(clientesFrequentes);
    }

}
