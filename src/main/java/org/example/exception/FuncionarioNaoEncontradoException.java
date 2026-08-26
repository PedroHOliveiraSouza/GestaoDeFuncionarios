package org.example.exception;

public class FuncionarioNaoEncontradoException extends RuntimeException{
    public FuncionarioNaoEncontradoException(long id){
        super("Funcionario não encontrado com o id:" + id);
    }
}

