package org.example.ui;

import org.example.model.*;
import org.example.service.ServicoEfetivacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private List<Funcionario> funcionarios = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarFuncionario();
                case 2 -> listarFuncionarios();
                case 3 -> calcularFolhaPagamento();
                case 4 -> efetivarEstagiario();
                case 5 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        } while (opcao != 5);
    }

    private void exibirMenu() {
        System.out.println("===== SISTEMA DE GESTÃO DE FUNCIONÁRIOS =====");
        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Listar funcionários");
        System.out.println("3 - Calcular folha de pagamento");
        System.out.println("4 - Efetivar estagiário");
        System.out.println("5 - Sair");
    }

    private void cadastrarFuncionario() {
        System.out.println("\n--- Cadastro de Funcionário ---");
        System.out.println("1 - CLT");
        System.out.println("2 - PJ");
        System.out.println("3 - Estagiário");
        System.out.println("4 - Gerente");
        int tipo = lerInt("Escolha o tipo: ");

        long id = funcionarios.size() + 1L;
        String nome = lerString("Nome: ");
        String cpf = lerString("CPF: ");
        String dataAdmissao = lerString("Data de admissão (aaaa-mm-dd): ");
        Cargo cargo = lerCargo();
        double salarioBase = lerDouble("Salário base: ");

        switch (tipo) {
            case 1 -> {
                double vt = lerDouble("Vale transporte: ");
                double vr = lerDouble("Vale refeição: ");
                funcionarios.add(new FuncionarioCLT( nome, cpf, dataAdmissao, cargo, salarioBase, vt, vr));
            }
            case 2 -> {
                String cnpj = lerString("CNPJ: ");
                double valorContrato = lerDouble("Valor do contrato: ");
                funcionarios.add(new FuncionarioPJ(nome, cpf, dataAdmissao, cargo, salarioBase, cnpj, valorContrato));
            }
            case 3 -> {
                String instituicao = lerString("Instituição de ensino: ");
                String dataFim = lerString("Data fim do estágio (aaaa-mm-dd): ");
                double vt = lerDouble("Vale transporte: ");
                funcionarios.add(new Estagiario( nome, cpf, dataAdmissao, cargo, salarioBase, instituicao, dataFim, vt));
            }
            case 4 -> {
                double valeTranspote = lerDouble("Vale transporte: ");
                double valeRefeicao = lerDouble("Vale refeição: ");
                Gerente gerente = new Gerente(nome, cpf, dataAdmissao, cargo, salarioBase,valeTranspote,valeRefeicao);
                double bonusPorMeta = lerDouble("Bônus por meta: ");
                gerente.setBonusPorMeta(bonusPorMeta);
                funcionarios.add(gerente);
            }
            default -> System.out.println("Tipo inválido! Cadastro cancelado.");
        }
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void listarFuncionarios() {
        System.out.println("\n  Lista de Funcionários  ");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            System.out.println("Tipo: " + f.getClass().getSimpleName());
            System.out.println(f.getDadosFuncionario());
            System.out.printf("Salário: R$ %.2f | Benefícios: R$ %.2f | Impostos: R$ %.2f%n",
                    f.calcularSalario(), f.calcularBeneficios(), f.calcularImpostos());
            System.out.println("\n");
        }
    }

    private void calcularFolhaPagamento() {
        System.out.println("\n--- Folha de Pagamento Consolidada ---");
        double totalSalarios = 0;
        double totalBeneficios = 0;
        double totalImpostos = 0;

        for (Funcionario f : funcionarios) {
            totalSalarios += f.calcularSalario();
            totalBeneficios += f.calcularBeneficios();
            totalImpostos += f.calcularImpostos();
        }

        System.out.printf("Total em salários: R$ %.2f%n", totalSalarios);
        System.out.printf("Total em benefícios: R$ %.2f%n", totalBeneficios);
        System.out.printf("Total em impostos: R$ %.2f%n", totalImpostos);
        System.out.printf("Custo total da folha: R$ %.2f%n", totalSalarios + totalBeneficios);
    }

    private void efetivarEstagiario() {
        System.out.println("\n Efetivar Estagiário ");
        List<Estagiario> estagiarios = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (f instanceof Estagiario e) {
                estagiarios.add(e);
            }
        }

        if (estagiarios.isEmpty()) {
            System.out.println("Nenhum estagiário cadastrado.");
            return;
        }

        for (int i = 0; i < estagiarios.size(); i++) {
            System.out.println((i + 1) + " - " + estagiarios.get(i).getNome());
        }
        int escolha = lerInt("Escolha o estagiário para efetivar: ");

        if (escolha < 1 || escolha > estagiarios.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Estagiario selecionado = estagiarios.get(escolha - 1);
        double valeRefeicao = lerDouble("Vale refeição para o novo CLT: ");
        FuncionarioCLT novoCLT = ServicoEfetivacao.efetivar(selecionado, valeRefeicao, funcionarios);
        System.out.println("Efetivado com sucesso: " + novoCLT.getDadosFuncionario());
    }

    private int lerInt(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Digite um número válido.");
            scanner.next();
            System.out.print(mensagem);
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Digite um valor válido.");
            scanner.next();
            System.out.print(mensagem);
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private Cargo lerCargo() {
        System.out.println("Cargos disponíveis: ");
        for (Cargo c : Cargo.values()) {
            System.out.println("- " + c);
        }
        while (true) {
            System.out.print("Digite o cargo: ");
            String entrada = scanner.nextLine().toUpperCase();
            try {
                return Cargo.valueOf(entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Cargo inválido, tente novamente.");
            }
        }
    }
}