package org.example;

public class FuncionarioCLT extends Funcionario{
    private double valeTransporte;
    private double valeRefeicao;



    public FuncionarioCLT(long id, String nome, String cpf, String dataAdmissao, String cargo, double salarioBase, double valeTransporte, double valeRefeicao) {
        super(id, nome, cpf, dataAdmissao, cargo, salarioBase);
        this.valeTransporte = valeTransporte;
        this.valeRefeicao = valeRefeicao;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase();
    }

    @Override
    public double calcularBeneficios() {
        return valeTransporte + valeRefeicao;
    }

    @Override
    public double calcularImpostos() {
        return calcularINSS(getSalarioBase());
    }
    private double calcularINSS(double salario){
        if (salario <= 1621.00) {
            return salario * 0.075;
        } else if (salario <= 2902.84) {
            return (salario * 0.09) - 23.66;
        } else if (salario <= 4354.27) {
            return (salario * 0.12) - 110.75;
        } else {
            double inss = (salario * 0.14) - 197.83;
            return Math.min(inss, 988.09);
        }
    }
    public double getValeTransporte() {
        return valeTransporte;
    }

    public void setValeTransporte(double valeTransporte) {
        this.valeTransporte = valeTransporte;
    }

    public double getValeRefeicao() {
        return valeRefeicao;
    }

    public void setValeRefeicao(double valeRefeicao) {
        this.valeRefeicao = valeRefeicao;
    }
}
