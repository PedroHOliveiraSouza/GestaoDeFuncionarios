package org.example;

public abstract class Funcionario {
    private long id;
    private String nome;
    private String cpf;
    private String dataAdmissao;
    private String cargo;
    private double salarioBase;

    public Funcionario(long id,String nome,String cpf,String dataAdmissao,String cargo,
                       double salarioBase){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataAdmissao =  dataAdmissao;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
    }
    public abstract double calcularSalario();
    public abstract double calcularBeneficios();
    public abstract double calcularImpostos();

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getDadosFuncionario(){
        return "ID: " + id + " | Nome: " + nome + " | Cargo: " + cargo;
    }
}
