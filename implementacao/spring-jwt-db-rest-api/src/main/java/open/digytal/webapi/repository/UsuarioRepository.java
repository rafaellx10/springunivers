package open.digytal.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import open.digytal.webapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	Usuario findByLogin(String login);
}
