package org.example.service;
import org.example.exception.FuncionarioNaoEncontradoException;
import org.example.model.Estagiario;
import org.example.model.Funcionario;
import org.example.model.FuncionarioCLT;
import org.example.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario cadastrar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(id));
    }

    public void remover(Long id) {
        Funcionario funcionario = buscarPorId(id); // valida existência antes de tentar remover
        funcionarioRepository.delete(funcionario);
    }

    public double calcularFolhaConsolidada() {
        return listarTodos().stream()
                .mapToDouble(Funcionario::calcularSalario)
                .sum();
    }

    public FuncionarioCLT efetivarEstagiario(Long estagiarioId, double valeRefeicao) {
        Funcionario funcionario = buscarPorId(estagiarioId);

        if (!(funcionario instanceof Estagiario estagiario)) {
            throw new IllegalArgumentException(
                    "O funcionário informado não é um estagiário e não pode ser efetivado.");
        }

        FuncionarioCLT novoCLT = new FuncionarioCLT(
                estagiario.getNome(),
                estagiario.getCpf(),
                estagiario.getDataAdmissao(),
                estagiario.getCargo(),
                estagiario.getSalarioBase(),
                estagiario.getValeTransporte(),
                valeRefeicao
        );

        funcionarioRepository.delete(estagiario);
        return funcionarioRepository.save(novoCLT);
    }
}

