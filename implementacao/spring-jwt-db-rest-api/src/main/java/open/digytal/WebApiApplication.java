package open.digytal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class WebApiApplication {// WAR --> extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(WebApiApplication.class, args);
	}
}
