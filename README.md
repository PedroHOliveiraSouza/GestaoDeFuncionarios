# Sistema de Gestão de Funcionários (versão console)

Aplicação de console em Java puro pra gerenciar funcionários de diferentes tipos (CLT, PJ, Estagiário, Gerente). Projeto de portfólio feito pra praticar orientação a objetos — principalmente herança e polimorfismo — antes de evoluir pra uma API REST com Spring Boot.

> Essa é a versão inicial do projeto, sem framework nenhum, rodando direto no terminal. A versão com API REST (Spring Boot, banco de dados, testes automatizados) está na branch [`feature/spring-boot-version`](https://github.com/PedroHOliveiraSouza/GestaoDeFuncionarios/tree/feature/spring-boot-version).

## Tecnologias utilizadas

- **Java 26**
- **Maven** — controla o build do projeto (sem dependências externas nessa versão, só o compilador)

## Conceitos de orientação a objetos praticados

- **Herança** — `Funcionario` é a classe base abstrata; `FuncionarioCLT`, `FuncionarioPJ`, `Estagiario` e `Gerente` herdam dela.
- **Polimorfismo** — cada subclasse calcula salário, benefícios e impostos do seu próprio jeito, sobrescrevendo os métodos abstratos `calcularSalario()`, `calcularBeneficios()` e `calcularImpostos()`.
- **Encapsulamento** — atributos privados com getters e setters.

## Estrutura do projeto

- **`model`** — as classes de funcionário (`Funcionario` e as subclasses) e o enum `Cargo`.
- **`interfaces`** — `Promovivel`, interface pensada pra funcionários que podem ser promovidos.
- **`service`** — `ServicoEfetivacao`, responsável por transformar um `Estagiario` em `FuncionarioCLT`.
- **`ui`** — `Menu`, cuida de toda a interação com o usuário pelo terminal (ler entrada, mostrar opções).
- **`Main`** — ponto de entrada da aplicação.

## Como usar

Ao rodar o programa, aparece um menu com estas opções:

```
1 - Cadastrar funcionário
2 - Listar funcionários
3 - Calcular folha de pagamento
4 - Efetivar estagiário
5 - Sair
```

Os dados ficam guardados só na memória, durante a execução — não há banco de dados nessa versão (isso só entra na versão Spring Boot).

## Como rodar

O jeito mais simples é pelo IntelliJ: clique com o botão direito em `Main.java` → **Run 'Main.main()'**.

Pelo terminal, sem plugin extra no `pom.xml`:

```bash
mvn compile
java -cp target/classes org.example.Main
```
