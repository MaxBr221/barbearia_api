package github.MaxBr221.repository;

import github.MaxBr221.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Long, Usuario> {
}
