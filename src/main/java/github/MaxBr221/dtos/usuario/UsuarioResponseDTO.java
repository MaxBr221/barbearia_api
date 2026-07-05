package github.MaxBr221.dtos.usuario;

import github.MaxBr221.model.Usuario;

public record UsuarioResponseDTO(Long id, String nome, String login, String telefone) {

    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getTelefone()
        );
    }
}
