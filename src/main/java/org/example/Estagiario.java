package org.example;

public class Estagiario extends Funcionario {
    private String instituicaoEnsino;
    private String dataFimEstagio;
    private int cargaHorariaSemanal = 30;
    private double valeTransporte;

    public Estagiario(long id, String nome, String cpf, String dataAdmissao, String cargo,
                      double salarioBase, String instituicaoEnsino, String dataFimEstagio,
                      double valeTransporte) {
        super(id, nome, cpf, dataAdmissao, cargo, salarioBase);
        this.instituicaoEnsino = instituicaoEnsino;
        this.dataFimEstagio = dataFimEstagio;
        this.valeTransporte = valeTransporte;
        // cargaHorariaSemanal já nasce com 30 (valor padrão), sem precisar passar no construtor
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase();
    }

    @Override
    public double calcularBeneficios() {
        return valeTransporte;
    }

    @Override
    public double calcularImpostos() {
        return 0;
    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }
    public void setInstituicaoEnsino(String instituicaoEnsino) {
        this.instituicaoEnsino = instituicaoEnsino;
    }

    public String getDataFimEstagio() {
        return dataFimEstagio;
    }
    public void setDataFimEstagio(String dataFimEstagio) {
        this.dataFimEstagio = dataFimEstagio;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }
    public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
        if (cargaHorariaSemanal > 30) {
            throw new IllegalArgumentException("Carga horária do estágio não pode exceder 30h semanais");
        }
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    public double getValeTransporte() {
        return valeTransporte;
    }

    public void setValeTransporte(double valeTransporte) {
        this.valeTransporte = valeTransporte;
    }
}