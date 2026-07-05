package github.MaxBr221.dtos.barbeiro;

import github.MaxBr221.model.Barbeiro;
import github.MaxBr221.model.Status;

public record BarbeiroResponseDTO(Long id,
                                  String nome,
                                  String telefone,
                                  String login,
                                  Status status) {
    public BarbeiroResponseDTO(Barbeiro barbeiro) {
        this(   barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getTelefone(),
                barbeiro.getLogin(),
                barbeiro.getStatus());
    }
}
