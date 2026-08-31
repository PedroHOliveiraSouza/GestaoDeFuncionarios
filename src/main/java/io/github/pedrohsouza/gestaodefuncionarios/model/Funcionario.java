package io.github.pedrohsouza.gestaodefuncionarios.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FuncionarioCLT.class, name = "CLT"),
        @JsonSubTypes.Type(value = FuncionarioPJ.class, name = "PJ"),
        @JsonSubTypes.Type(value = Estagiario.class, name = "ESTAGIARIO"),
        @JsonSubTypes.Type(value = Gerente.class, name = "GERENTE")
})
@Entity
@Table(name = "funcionario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_funcionario", discriminatorType = DiscriminatorType.STRING)
public abstract class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "A data de admissão é obrigatória")
    private String dataAdmissao;

    @NotNull(message = "O cargo é obrigatório")
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Positive(message = "O salário base deve ser maior que zero")
    private double salarioBase;
    protected Funcionario() {
    }
    protected Funcionario(String nome, String cpf, String dataAdmissao, Cargo cargo, double salarioBase) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario();
    public abstract double calcularBeneficios();
    public abstract double calcularImpostos();

    public String getDadosFuncionario() {
        return "ID: " + id + " | Nome: " + nome + " | Cargo: " + cargo;
    }

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