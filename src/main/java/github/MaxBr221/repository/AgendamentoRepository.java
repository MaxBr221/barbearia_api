package github.MaxBr221.repository;

import github.MaxBr221.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean findByData(LocalDateTime data);
    boolean findBarbeiroById(Long id);
    boolean existsServicoById(Long id);
}
