package org.example;

import org.example.model.*;
import org.example.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SistemaGestaoFuncionariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaGestaoFuncionariosApplication.class, args);
    }

    @Bean
    public CommandLineRunner testarRepository(FuncionarioRepository funcionarioRepository) {
        return args -> {

            FuncionarioCLT clt = new FuncionarioCLT(
                    "Ana", "111.111.111-11", "2023-01-10",
                    Cargo.ANALISTA, 4500.00, 200.00, 300.00
            );

            FuncionarioPJ pj = new FuncionarioPJ(
                    "Pedro", "222.222.222-22", "2022-05-20",
                    Cargo.CONSULTOR, 6000.00, "12.345.678/0001-90", 6000.00
            );

            Estagiario estagiario = new Estagiario(
                    "Raul", "333.333.333-33", "2024-03-01",
                    Cargo.ESTAGIARIO, 1600.00, "UCSal", "2025-12-31", 150.00
            );

            Gerente gerente = new Gerente(
                    "Diego ", "444.444.444-44", "2020-08-15",
                    Cargo.GERENTE, 8000.00, 250.00, 350.00
            );
            gerente.setBonusPorMeta(1500.00);

            funcionarioRepository.save(clt);
            funcionarioRepository.save(pj);
            funcionarioRepository.save(estagiario);
            funcionarioRepository.save(gerente);

            System.out.println("\n===== TESTE DE POLIMORFISMO VIA REPOSITORY =====\n");

            List<Funcionario> todos = funcionarioRepository.findAll();

            for (Funcionario f : todos) {
                System.out.printf("Tipo: %-15s | Nome: %-12s | Salário: R$ %.2f | Benefícios: R$ %.2f | Impostos: R$ %.2f%n",
                        f.getClass().getSimpleName(),
                        f.getNome(),
                        f.calcularSalario(),
                        f.calcularBeneficios(),
                        f.calcularImpostos());
            }
        };
    }
}