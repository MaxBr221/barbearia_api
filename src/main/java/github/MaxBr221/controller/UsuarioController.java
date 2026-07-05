package github.MaxBr221.controller;

import github.MaxBr221.dtos.usuario.UsuarioResponseDTO;
import github.MaxBr221.dtos.usuario.UsuarioResquestDTO;
import github.MaxBr221.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    private UsuarioService usuarioService;

    @PostMapping
    public UsuarioResponseDTO create(@RequestBody UsuarioResquestDTO usuarioResquestDTO){

    }
}
