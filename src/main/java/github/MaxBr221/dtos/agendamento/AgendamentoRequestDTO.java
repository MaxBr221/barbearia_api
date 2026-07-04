package github.MaxBr221.dtos.agendamento;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(Long idUser, Long idBarbeiro, Long idServico, LocalDateTime data) {
}
