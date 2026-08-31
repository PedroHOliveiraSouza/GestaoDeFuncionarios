package io.github.pedrohsouza.gestaodefuncionarios.controller;

import tools.jackson.databind.json.JsonMapper;
import io.github.pedrohsouza.gestaodefuncionarios.exception.FuncionarioNaoEncontradoException;
import io.github.pedrohsouza.gestaodefuncionarios.model.Cargo;
import io.github.pedrohsouza.gestaodefuncionarios.model.FuncionarioCLT;
import io.github.pedrohsouza.gestaodefuncionarios.service.FuncionarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(FuncionarioController.class)
class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @MockitoBean
    private FuncionarioService funcionarioService;

    @Test
    void cadastrar_deveRetornar201_quandoDadosValidos() throws Exception {
        FuncionarioCLT clt = new FuncionarioCLT(
                "Ana", "111.111.111-11", "2023-01-10", Cargo.ANALISTA,
                4500.00, 200.00, 300.00);

        when(funcionarioService.cadastrar(any())).thenReturn(clt);

        String json = """
                {
                  "tipo": "CLT",
                  "nome": "Ana",
                  "cpf": "111.111.111-11",
                  "dataAdmissao": "2023-01-10",
                  "cargo": "ANALISTA",
                  "salarioBase": 4500.00,
                  "valeTransporte": 200.00,
                  "valeRefeicao": 300.00
                }
                """;

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Ana"));
    }

    @Test
    void cadastrar_deveRetornar400_quandoNomeVazio() throws Exception {
        String json = """
                {
                  "tipo": "CLT",
                  "nome": "",
                  "cpf": "111.111.111-11",
                  "dataAdmissao": "2023-01-10",
                  "cargo": "ANALISTA",
                  "salarioBase": 4500.00,
                  "valeTransporte": 200.00,
                  "valeRefeicao": 300.00
                }
                """;

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void buscarPorId_deveRetornar404_quandoNaoExiste() throws Exception {
        when(funcionarioService.buscarPorId(99L))
                .thenThrow(new FuncionarioNaoEncontradoException(99L));

        mockMvc.perform(get("/funcionarios/99"))
                .andExpect(status().isNotFound());
    }
}