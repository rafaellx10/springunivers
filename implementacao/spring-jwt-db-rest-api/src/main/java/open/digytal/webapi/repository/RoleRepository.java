package open.digytal.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import open.digytal.webapi.model.Role;

public interface RoleRepository  extends JpaRepository<Role, String> {
	Role findByNome(String nome);
}
