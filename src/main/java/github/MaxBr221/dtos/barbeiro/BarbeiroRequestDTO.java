package github.MaxBr221.dtos.barbeiro;

import github.MaxBr221.model.Status;

public record BarbeiroRequestDTO(String nome,
                                 String telefone,
                                 String login,
                                 Status status) {
}
