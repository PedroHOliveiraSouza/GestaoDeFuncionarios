package io.github.pedrohsouza.gestaodefuncionarios.controller;

import jakarta.validation.Valid;
import io.github.pedrohsouza.gestaodefuncionarios.model.Funcionario;
import io.github.pedrohsouza.gestaodefuncionarios.model.FuncionarioCLT;
import io.github.pedrohsouza.gestaodefuncionarios.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@Valid @RequestBody Funcionario funcionario) {
        Funcionario salvo = funcionarioService.cadastrar(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<Funcionario> listarTodos() {
        return funcionarioService.listarTodos();
    }

    @GetMapping("/folha")
    public double calcularFolhaConsolidada() {
        return funcionarioService.calcularFolhaConsolidada();
    }

    @GetMapping("/{id}")
    public Funcionario buscarPorId(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        funcionarioService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/efetivar")
    public FuncionarioCLT efetivarEstagiario(@PathVariable Long id, @RequestParam double valeRefeicao) {
        return funcionarioService.efetivarEstagiario(id, valeRefeicao);
    }
}