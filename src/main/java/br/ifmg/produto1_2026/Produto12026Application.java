package br.ifmg.produto1_2026;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Produto12026Application implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

//        String teste = passwordEncoder.encode("123456");
//        System.out.println("===============================");
//        System.out.println(teste);
//        System.out.println("===============================");

    }

	public static void main(String[] args) {
		SpringApplication.run(Produto12026Application.class, args);
	}

}
