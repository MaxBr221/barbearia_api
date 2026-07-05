package github.MaxBr221.dtos.servico;

import github.MaxBr221.model.Servico;

public record ServicoResponseDTO(Long id, String nome, double preco, String descricao) {
    public ServicoResponseDTO (Servico servico){
        this(servico.getId(), servico.getNome(), servico.getPreco(), servico.getDescricao());
    }
}
