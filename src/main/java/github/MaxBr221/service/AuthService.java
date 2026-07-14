package github.MaxBr221.service;

import github.MaxBr221.dtos.auth.Cadastro;
import github.MaxBr221.dtos.usuario.UsuarioResponseDTO;
import github.MaxBr221.exception.EventFullException;
import github.MaxBr221.model.Role;
import github.MaxBr221.model.Usuario;
import github.MaxBr221.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado!"));
    }
    public UsuarioResponseDTO cadastrarUsuario(Cadastro cadastro){
        if(usuarioRepository.existsByLogin(cadastro.login())){
            throw new EventFullException("Usuário já existente!");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(cadastro.senha());
        Usuario usuario = new Usuario(cadastro.nome(), cadastro.login(), senhaCriptografada, cadastro.telefone(), Role.USER);
        return new UsuarioResponseDTO(usuario);
    }


}
