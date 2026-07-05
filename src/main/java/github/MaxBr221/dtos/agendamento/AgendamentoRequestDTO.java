package github.MaxBr221.dtos.agendamento;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(Long idUser, Long idBarbeiro, Long idServico, LocalDateTime data) {
}
