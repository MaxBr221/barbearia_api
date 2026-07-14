package github.MaxBr221.dtos.usuario;

public record ClienteFrequencia(Long idCliente, String nome, int totalAgendamentos) {
    public ClienteFrequencia(ClienteFrequencia usuarios) {
        this(usuarios.idCliente,
                usuarios.nome,
                usuarios.totalAgendamentos);
    }
}
