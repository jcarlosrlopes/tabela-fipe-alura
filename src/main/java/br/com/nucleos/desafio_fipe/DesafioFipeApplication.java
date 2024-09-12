package br.com.nucleos.desafio_fipe;

import br.com.nucleos.desafio_fipe.menu.Principal;
import br.com.nucleos.desafio_fipe.model.VeiculoPesquisa;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioFipeApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(DesafioFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibirMenu();
	}
}
