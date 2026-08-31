package io.github.pedrohsouza.gestaodefuncionarios.service;

import io.github.pedrohsouza.gestaodefuncionarios.model.Estagiario;
import io.github.pedrohsouza.gestaodefuncionarios.model.Funcionario;
import io.github.pedrohsouza.gestaodefuncionarios.model.FuncionarioCLT;

import java.util.List;

public class ServicoEfetivacao {
    public static FuncionarioCLT efetivar(Estagiario estagiario, double valeRefeicao, List<Funcionario> funcionarios) {
        FuncionarioCLT novoCLT = new FuncionarioCLT(
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

