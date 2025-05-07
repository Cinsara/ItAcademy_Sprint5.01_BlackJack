package cat.itacademy.s05.t01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(
		scanBasePackages = {
				"cat.itacademy.s05.t01.services.GameService",
				"cat.itacademy.s05.t01.repository.GameRepository"
		})
public class S05T01Application {

	public static void main(String[] args) {
		SpringApplication.run(S05T01Application.class, args);
	}
}
