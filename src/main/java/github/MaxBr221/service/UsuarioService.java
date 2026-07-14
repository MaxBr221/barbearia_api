package github.MaxBr221.service;

import github.MaxBr221.dtos.usuario.UsuarioResponseDTO;
import github.MaxBr221.dtos.usuario.UsuarioResquestDTO;
import github.MaxBr221.exception.EventFullException;
import github.MaxBr221.model.Usuario;
import github.MaxBr221.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    //não vai precisa desse logo logo
    public UsuarioResponseDTO create(UsuarioResquestDTO userDTO){
        if(usuarioRepository.existsByLogin(userDTO.login())){
            throw new EventFullException("Usuário com login já existente!");
        }
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(userDTO, userDTO);
        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(salvo);
    }
    public UsuarioResponseDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado!"));
        return new UsuarioResponseDTO(usuario);
    }
    public List<UsuarioResponseDTO> findAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> new UsuarioResponseDTO(usuario))
                .toList();
    }
    public void delete(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado!"));

        usuarioRepository.delete(usuario);
    }
    public UsuarioResponseDTO update(Long id, UsuarioResquestDTO userDTO){
        Usuario usuarioBuscado = usuarioRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Usuário não encontrado!"));

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(userDTO, usuario);
        Usuario userSalvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(userSalvo);
    }
}
