package github.MaxBr221.repository;

import github.MaxBr221.dtos.usuario.ClienteFrequencia;
import github.MaxBr221.model.Agendamento;
import github.MaxBr221.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean findByData(LocalDateTime data);
    boolean findBarbeiroById(Long id);
    boolean existsServicoById(Long id);
    List<Agendamento> findByDataHoraIncioBetween(LocalDateTime inicio, LocalDateTime fim);
    List<Agendamento> findByStatus(StatusAgendamento status);

    @Query("""
         SELECT new github.MaxBr221.dtos.dashboard.ClienteFrequenteDTO(
                c.id,
                c.nome,
                COUNT(a)
            )
            FROM Agendamento a
            JOIN a.cliente c
            WHERE a.status = github.MaxBr221.model.enums.StatusAgendamento.FINALIZADO
            GROUP BY c.id, c.nome
            ORDER BY COUNT(a) DESC 
           """)
    List<ClienteFrequencia> usuariosMaisFrequentes();
}
