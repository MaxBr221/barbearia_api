package github.MaxBr221.dtos.usuario;

public record ClienteFrequencia(Long idCliente, String nome, Long totalAgendamentos) {
    public ClienteFrequencia(ClienteFrequencia usuarios) {
        this(usuarios.idCliente,
                usuarios.nome,
                usuarios.totalAgendamentos);
    }
}
