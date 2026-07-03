package github.MaxBr221.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "barbeiro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Barbeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private String telefone;



}
