package org.example.model;

public class FuncionarioPJ extends Funcionario {
    private String cnpj;
    private double valorContrato;
    public FuncionarioPJ(String nome, String cpf, String dataAdmissao, Cargo cargo, double salarioBase, String cnpj, double valorContrato) {
        super(nome, cpf, dataAdmissao, cargo, salarioBase);
        this.cnpj = cnpj;
        this.valorContrato = valorContrato;
    }

    @Override
    public double calcularSalario() {
        return valorContrato;
    }

    @Override
    public double calcularBeneficios() {
        return 0;
    }

    @Override
    public double calcularImpostos() {
        /* PJ não tem retenção de IRRF/INSS pela empresa contratante,
         pois o próprio prestador recolhe seus impostos */
        return 0;
    }
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public double getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(double valorContrato) {
        this.valorContrato = valorContrato;
    }
}
