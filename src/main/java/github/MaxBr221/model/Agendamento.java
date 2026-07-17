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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "data_agendamento")
    private LocalDateTime data;
    @Column(name = "user_id", nullable = false)
    private Long idUser;

    @Column(name = "barbeiro_id", nullable = false)
    private Long idBarbeiro;

    @Column(name = "servico_id", nullable = false)
    private Long idServico;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status_agendamento")
    private StatusAgendamento statusAgendamento;


}
