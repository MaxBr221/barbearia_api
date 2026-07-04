package github.MaxBr221.dtos.agendamento;

import github.MaxBr221.model.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(Long id, LocalDateTime data, Long idUser, Long idBarbeiro, Long idServico, StatusAgendamento statusAgendamento) {
}
