package github.MaxBr221.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, name = "data_agendamento")
    private LocalDateTime data;
    @Column(nullable = false, name = "user_id")
    private Long idUser;
    @Column(nullable = false, name = "barbeiro_id")
    private Long idBarbeiro;
    @Column(nullable = false, name = "servico_id")
    private Long idServico;
    @Column(nullable = false, name = "status_agendamento")
    private StatusAgendamento statusAgendamento;


}
