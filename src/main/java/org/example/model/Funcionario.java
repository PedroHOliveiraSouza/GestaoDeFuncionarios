package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_funcionario", discriminatorType = DiscriminatorType.STRING)
public abstract class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String dataAdmissao;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    private double salarioBase;

    // Construtor vazio - o JPA exige um construtor sem argumentos
    protected Funcionario() {
    }

    // Construtor com os dados, pra você usar ao criar objetos novos
    protected Funcionario(String nome, String cpf, String dataAdmissao, Cargo cargo, double salarioBase) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
    }

    // Métodos abstratos - continuam exatamente como no seu projeto Java puro
    public abstract double calcularSalario();
    public abstract double calcularBeneficios();
    public abstract double calcularImpostos();

    // Método concreto, herdado por todo mundo
    public String getDadosFuncionario() {
        return "ID: " + id + " | Nome: " + nome + " | Cargo: " + cargo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
}