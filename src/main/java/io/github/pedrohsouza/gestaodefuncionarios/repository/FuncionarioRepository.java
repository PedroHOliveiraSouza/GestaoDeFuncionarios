package io.github.pedrohsouza.gestaodefuncionarios.repository;

import io.github.pedrohsouza.gestaodefuncionarios.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
