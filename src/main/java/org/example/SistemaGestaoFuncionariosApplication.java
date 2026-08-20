package org.example;

import org.example.model.Cargo;
import org.example.model.FuncionarioCLT;
import org.example.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SistemaGestaoFuncionariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaGestaoFuncionariosApplication.class, args);
    }

    @Bean
    public CommandLineRunner testarRepository(FuncionarioRepository funcionarioRepository) {
        return args -> {
            FuncionarioCLT novo = new FuncionarioCLT(
                    "Ana Silva", "111.111.111-11", "2023-01-10",
                    Cargo.ANALISTA, 4500.00, 200.00, 300.00
            );

            funcionarioRepository.save(novo);

            System.out.println("Funcionário salvo com sucesso! ID gerado: " + novo.getId());
        };
    }
}