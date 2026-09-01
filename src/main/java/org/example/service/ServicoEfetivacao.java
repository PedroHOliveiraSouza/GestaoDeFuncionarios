package org.example.service;

import org.example.model.Estagiario;
import org.example.model.Funcionario;
import org.example.model.FuncionarioCLT;

import java.util.List;

public class ServicoEfetivacao {
    public static FuncionarioCLT efetivar(Estagiario estagiario, double valeRefeicao, List<Funcionario> funcionarios) {
        FuncionarioCLT novoCLT = new FuncionarioCLT(
                estagiario.getId(),
                estagiario.getNome(),
                estagiario.getCpf(),
                estagiario.getDataAdmissao(),
                estagiario.getCargo(),
                estagiario.getSalarioBase(),
                estagiario.getValeTransporte(),
                valeRefeicao
        );
        funcionarios.remove(estagiario);
        funcionarios.add(novoCLT);

        return novoCLT;
    }
}

