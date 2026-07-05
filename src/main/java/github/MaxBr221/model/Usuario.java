package github.MaxBr221.model;

import github.MaxBr221.dtos.usuario.UsuarioResquestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@Getter
@Entity
@Setter
@AllArgsConstructor
@Table(name = "usuario")
@NoArgsConstructor
public class Usuario {
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
    private String telefone;

    public Usuario(UsuarioResquestDTO usuarioResquestDTO){
        BeanUtils.copyProperties(usuarioResquestDTO, this);
    }


}
