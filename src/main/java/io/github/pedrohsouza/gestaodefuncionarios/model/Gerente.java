package io.github.pedrohsouza.gestaodefuncionarios.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "gerente")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("GERENTE")

public class Gerente extends FuncionarioCLT {

    private double bonusPorMeta;
    private double percentualComissao;
    private List<String> equipe = new ArrayList<>();

    protected Gerente(){
        super();
    }

    public Gerente( String nome, String cpf, String dataAdmissao,
                   Cargo cargo, double salarioBase,
                   double valeTransporte, double valeRefeicao) {
        super(nome, cpf, dataAdmissao, cargo, salarioBase, valeTransporte, valeRefeicao);
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