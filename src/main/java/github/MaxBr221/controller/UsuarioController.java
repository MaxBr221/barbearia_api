package github.MaxBr221.controller;

import github.MaxBr221.dtos.usuario.UsuarioResponseDTO;
import github.MaxBr221.dtos.usuario.UsuarioResquestDTO;
import github.MaxBr221.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioResquestDTO usuarioResquestDTO){
        UsuarioResponseDTO usuario = usuarioService.create(usuarioResquestDTO);
        log.info("Salvando usuário {}!", usuario.nome());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        log.info("Deletando usuário!");
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id,@RequestBody UsuarioResquestDTO usuarioResquestDTO){
        UsuarioResponseDTO usuarioUpdate = usuarioService.update(id, usuarioResquestDTO);
        log.info("Atualizando usuário do id {}", usuarioUpdate.id());
        return ResponseEntity.ok(usuarioUpdate);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id){
        UsuarioResponseDTO usuarioBuscado = usuarioService.findById(id);
        log.info("Usuário buscado {} buscado com sucesso!", usuarioBuscado.nome());
        return ResponseEntity.ok(usuarioBuscado);
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll(){
        List<UsuarioResponseDTO> listaUsuario = usuarioService.findAll();
        log.info("Listando todos os usuários!");
        return ResponseEntity.ok(listaUsuario);
    }
}
