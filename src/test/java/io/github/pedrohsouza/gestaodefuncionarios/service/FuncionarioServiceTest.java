package io.github.pedrohsouza.gestaodefuncionarios.service;

import io.github.pedrohsouza.gestaodefuncionarios.exception.FuncionarioNaoEncontradoException;
import io.github.pedrohsouza.gestaodefuncionarios.model.Cargo;
import io.github.pedrohsouza.gestaodefuncionarios.model.Estagiario;
import io.github.pedrohsouza.gestaodefuncionarios.model.FuncionarioCLT;
import io.github.pedrohsouza.gestaodefuncionarios.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Test
    void buscarPorId_deveLancarExcecao_quandoIdNaoExiste() {
        when(funcionarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> funcionarioService.buscarPorId(99L))
                .isInstanceOf(FuncionarioNaoEncontradoException.class)
                .hasMessageContaining("99");
    }

    @Test
    void calcularFolhaConsolidada_deveSomarSalarioDeTodos() {
        FuncionarioCLT clt = new FuncionarioCLT(
                "Ana", "111.111.111-11", "2023-01-10", Cargo.ANALISTA,
                4500.00, 200.00, 300.00);
        Estagiario estagiario = new Estagiario(
                "Raul", "333.333.333-33", "2024-03-01", Cargo.ESTAGIARIO,
                1600.00, "UCSal", "2025-12-31", 150.00);

        when(funcionarioRepository.findAll()).thenReturn(List.of(clt, estagiario));

        double folha = funcionarioService.calcularFolhaConsolidada();

        assertThat(folha).isEqualTo(6100.00);
    }

    @Test
    void efetivarEstagiario_deveConverterParaCLT() {
        Estagiario estagiario = new Estagiario(
                "Raul", "333.333.333-33", "2024-03-01", Cargo.ESTAGIARIO,
                1600.00, "UCSal", "2025-12-31", 150.00);

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(estagiario));
        when(funcionarioRepository.save(any(FuncionarioCLT.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        FuncionarioCLT resultado = funcionarioService.efetivarEstagiario(1L, 300.00);

        assertThat(resultado.getNome()).isEqualTo("Raul");
        assertThat(resultado.getValeRefeicao()).isEqualTo(300.00);
        verify(funcionarioRepository).delete(estagiario);
    }

    @Test
    void efetivarEstagiario_deveLancarExcecao_quandoNaoEhEstagiario() {
        FuncionarioCLT clt = new FuncionarioCLT(
                "Ana", "111.111.111-11", "2023-01-10", Cargo.ANALISTA,
                4500.00, 200.00, 300.00);

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(clt));

        assertThatThrownBy(() -> funcionarioService.efetivarEstagiario(1L, 300.00))
                .isInstanceOf(IllegalArgumentException.class);
    }
}