package org.example;

import org.example.model.*;
import org.example.service.ServicoEfetivacao;
import org.example.ui.Menu;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.iniciar();

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new FuncionarioCLT("Ana Silva", "865.333.222-99","2023-05-06",Cargo.DIRETOR,1000.000,
                800.00,1000.00));

        funcionarios.add(new FuncionarioPJ( "Bruno Costa", "222.222.222-22",
                "2022-05-20", Cargo.ANALISTA, 6000.00, "12.345.678/0001-90", 6000.00));

        funcionarios.add(new Estagiario( "Carla Souza", "333.333.333-33",
                "2024-03-01",Cargo.ESTAGIARIO, 1600.00,
                "UCSal", "2025-12-31", 150.00));

        Gerente gerente = new Gerente("Diego Alves", "444.444.444-44", "2020-08-15",
                Cargo.GERENTE, 8000.00,300.00,500.00);
        gerente.setBonusPorMeta(1500.00);
        funcionarios.add(gerente);
        Estagiario carla = new Estagiario( "Carla Souza", "333.333.333-33",
                "2024-03-01", Cargo.ANALISTA, 1600.00, "UCSal", "2025-12-31", 150.00);
        funcionarios.add(carla);

        FuncionarioCLT carlaEfetivada = ServicoEfetivacao.efetivar(carla, 300.00, funcionarios);
        System.out.println("Efetivada: " + carlaEfetivada.getDadosFuncionario());

        for (Funcionario f : funcionarios) {
            System.out.println("=== " + f.getDadosFuncionario() + " ===");
            System.out.println("Tipo real do objeto: " + f.getClass().getSimpleName());
            System.out.printf("Salário: R$ %.2f%n", f.calcularSalario());
            System.out.printf("Benefícios: R$ %.2f%n", f.calcularBeneficios());
            System.out.printf("Impostos: R$ %.2f%n", f.calcularImpostos());
            System.out.println();
        }
    }
}