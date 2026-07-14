package github.MaxBr221.controller;

import github.MaxBr221.dtos.auth.Cadastro;
import github.MaxBr221.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private AuthService authService;

    @PostMapping("/cadastro)")
    public ResponseEntity cadastro(@RequestBody Cadastro cadastro){
        authService.cadastrarUsuario(cadastro);
        log.info("Usuário {} cadastrado com sucesso!", cadastro.nome());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
