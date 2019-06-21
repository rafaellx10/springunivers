package open.digytal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private static final Logger LOGGER = LogManager.getLogger(WebApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WebApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(UsuarioRepository repository,RoleRepository roleRepository,PasswordEncoder encoder) {
		return args -> {
			if(repository.findByLogin("admin")==null) {
				Role r = new Role(Roles.ADMIN.name());
				roleRepository.save(r);
				LOGGER.info("Criando a Role ADMIN ");
				Usuario user = new Usuario();
				user.setLogin("admin");
				String senha = encoder.encode("admin");
				user.setSenha(senha);
				user.setNome("GLEYSON SAMPAIO");
				user.setEmail("gleyson.sampaio@digytal.com.br");
				user.addRole(r);
				repository.save(user);
				LOGGER.info("Criando o Usuário ADMIN ");
			};
		};
	}
}
