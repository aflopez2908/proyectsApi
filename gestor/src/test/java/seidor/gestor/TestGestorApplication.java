package seidor.gestor;

import org.springframework.boot.SpringApplication;

public class TestGestorApplication {

	public static void main(String[] args) {
		SpringApplication.from(GestorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
