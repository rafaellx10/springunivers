package open.digytal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import open.digytal.webapi.model.Role;
import open.digytal.webapi.model.Roles;
import open.digytal.webapi.model.Usuario;
import open.digytal.webapi.repository.RoleRepository;
import open.digytal.webapi.repository.UsuarioRepository;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class WebApiApplication {// WAR --> extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(WebApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(UsuarioRepository repository,RoleRepository roleRepository) {
		return args -> {
			if(repository.findByLogin("admin")==null) {
				Role r = new Role(Roles.ADMIN.name());
				roleRepository.save(r);
				Usuario user = new Usuario();
				user.setLogin("admin");
				user.setSenha("admin");
				user.setNome("GLEYSON SAMPAIO");
				user.setEmail("gleyson.sampaio@digytal.com.br");
				user.addRole(r);
				repository.save(user);
			};
		};
	}
}
