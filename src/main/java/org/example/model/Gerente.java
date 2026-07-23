package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends FuncionarioCLT {

    private double bonusPorMeta;
    private double percentualComissao;
    private List<String> equipe = new ArrayList<>();

    public Gerente(long id, String nome, String cpf, String dataAdmissao,
                   Cargo cargo, double salarioBase,
                   double valeTransporte, double valeRefeicao) {
        super(id, nome, cpf, dataAdmissao, cargo, salarioBase, valeTransporte, valeRefeicao);
    }

    @Override
    public double calcularSalario() {
        double salarioBaseCLT = super.calcularSalario();
        return salarioBaseCLT + bonusPorMeta + calcularBonusEquipe();
    }

    public double calcularBonusEquipe() {
        return percentualComissao * equipe.size();
    }

    public double getBonusPorMeta() {
        return bonusPorMeta;
    }

    public void setBonusPorMeta(double bonusPorMeta) {
        this.bonusPorMeta = bonusPorMeta;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    public List<String> getEquipe() {
        return new ArrayList<>(equipe); // devolve uma cópia, não a lista original
    }

    public void adicionarMembroEquipe(String nomeFuncionario) {
        equipe.add(nomeFuncionario);
    }

    public void removerMembroEquipe(String nomeFuncionario) {
        equipe.remove(nomeFuncionario);
    }
}