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
    List<Agendamento> findByIdBarbeiroAndDataBetween(Long barbeiro_id, LocalDateTime incio, LocalDateTime fim);
    List<Agendamento> findAllByidUser(Long idUser);
    boolean findBarbeiroById(Long id);
    boolean existsServicoById(Long id);
    List<Agendamento> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);
    List<Agendamento> findBystatusAgendamento(StatusAgendamento status);

    @Query("SELECT u.id AS idCliente, u.nome AS nome, COUNT(a) AS totalAgendamentos " +
            "FROM Agendamento a " +
            "JOIN Usuario u ON a.idUser = u.id " +
            "WHERE a.statusAgendamento = github.MaxBr221.model.StatusAgendamento.FINALIZADO " +
            "GROUP BY u.id, u.nome " +
            "ORDER BY COUNT(a) DESC")
    List<ClienteFrequencia> usuariosMaisFrequentes();
}
