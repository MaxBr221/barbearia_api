package github.MaxBr221.service;

import github.MaxBr221.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
}
