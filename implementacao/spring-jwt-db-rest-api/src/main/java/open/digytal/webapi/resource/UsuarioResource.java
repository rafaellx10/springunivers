package open.digytal.webapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import open.digytal.webapi.model.Role;
import open.digytal.webapi.model.Roles;
import open.digytal.webapi.model.Usuario;
import open.digytal.webapi.repository.RoleRepository;
import open.digytal.webapi.repository.UsuarioRepository;
import open.digytal.webapi.secutiry.JwtSession;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping
	@ResponseBody
	@PreAuthorize(Roles.PRE_ADMIN)
	public List<Usuario> listar() {
		return repository.findAll();
	}
	@GetMapping(value="/roles")
	//@PreAuthorize(Roles.PRE_USER_ADMIN)
	public List<Role> listarRoles() {
		System.out.println(JwtSession.getLogin());
		return roleRepository.findAll();
	}
	@PostMapping
	//@PreAuthorize(Roles.PRE_ADMIN)
	public void incluir(@RequestBody Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		repository.save(usuario);
	}
	@PostMapping(value="/role")
	//@PreAuthorize(Roles.PRE_ADMIN)
	public void incluirRole(@RequestBody Role role) {
		roleRepository.save(role);
	}
}
