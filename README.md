# Sistema de Gestão de Funcionários

API REST para gerenciar funcionários de diferentes tipos (CLT, PJ, Estagiário, Gerente). Projeto de portfólio feito para mostrar orientação a objetos (herança, polimorfismo) evoluindo para uma arquitetura em camadas com Spring Boot e banco de dados.

## Tecnologias utilizadas

### Linguagem e build
- **Java 25**
- **Maven** — controla as dependências e o build do projeto

### Framework principal
- **Spring Boot 4.1** — usa a nova estrutura modular de starters da versão 4 (cada tecnologia tem seu próprio starter, incluindo starters específicos pra teste)
- **Spring Framework 7**
- **Spring MVC** (`spring-boot-starter-webmvc`) — camada REST
- **Spring Data JPA** (`spring-boot-starter-data-jpa`) — acesso ao banco
- **Bean Validation** (`spring-boot-starter-validation`), com **Hibernate Validator**

### Banco de dados
- **Hibernate ORM** — implementação de JPA
- **Jakarta Persistence API**
- **H2 Database** — banco de dados em memória, com console web habilitado (`spring-boot-h2console`)
- Estratégia de herança `JOINED` (`@Inheritance(strategy = InheritanceType.JOINED)`), com uma tabela própria pra cada tipo de funcionário

### JSON
- **Jackson 3** (pacote `tools.jackson.*`, padrão a partir do Spring Boot 4) — converte objetos Java em JSON e vice-versa
- `@JsonTypeInfo` / `@JsonSubTypes` (do módulo `jackson-annotations`, que continua em `com.fasterxml.jackson.annotation`) — permite ao Jackson identificar qual subtipo de `Funcionario` criar a partir do campo `tipo` no JSON

### Testes
- **JUnit 5** (Jupiter)
- **Mockito** — cria dados falsos (mocks) pros testes do Service
- **AssertJ** — deixa as verificações do teste mais fáceis de ler
- **Spring Test / MockMvc** (`spring-boot-starter-webmvc-test`) — testa a camada web com `@WebMvcTest`

### Outras dependências
- **Lombok** — reduz código repetitivo
- **Spring Boot DevTools** — reinicia a aplicação sozinha quando o código muda, durante o desenvolvimento

## Arquitetura

O projeto segue uma arquitetura em camadas:

```
Controller  →  Service  →  Repository  →  Banco (H2)
  (HTTP)        (regras)     (acesso ao banco)
```

- **`model`** — as entidades: `Funcionario` (classe base) e as subclasses `FuncionarioCLT`, `FuncionarioPJ`, `Estagiario` e `Gerente`, cada uma com sua própria forma de calcular salário, benefícios e impostos (polimorfismo).
- **`repository`** — `FuncionarioRepository`, interface do Spring Data JPA que fala com o banco.
- **`service`** — `FuncionarioService`, onde ficam as regras de negócio (cálculo da folha total, efetivação de estagiário, checagem se o funcionário existe). É a única camada que o Controller conhece.
- **`controller`** — `FuncionarioController`, expõe os endpoints REST e repassa toda regra de negócio pro Service.
- **`exception`** — `FuncionarioNaoEncontradoException` e `GlobalExceptionHandler` (`@RestControllerAdvice`), cuidam dos erros em um só lugar, transformando exceções de negócio em respostas HTTP adequadas.

## Endpoints

| Método | Rota                          | O que faz                                              |
|--------|--------------------------------|----------------------------------------------------------|
| POST   | `/funcionarios`                | Cadastra um funcionário novo (o campo `tipo` define a subclasse: `CLT`, `PJ`, `ESTAGIARIO`, `GERENTE`) |
| GET    | `/funcionarios`                | Lista todos os funcionários                              |
| GET    | `/funcionarios/{id}`           | Busca um funcionário pelo id                               |
| GET    | `/funcionarios/folha`          | Soma o salário de todos os funcionários                  |
| DELETE | `/funcionarios/{id}`           | Remove um funcionário                                     |
| POST   | `/funcionarios/{id}/efetivar`  | Transforma um estagiário em `FuncionarioCLT` (o parâmetro `valeRefeicao` vai na URL) |

### Exemplo de cadastro

```json
POST /funcionarios
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
```

## Tratamento de erros

Os erros são tratados em um só lugar, no `GlobalExceptionHandler`, que devolve sempre o mesmo formato de resposta, com `timestamp` e `mensagem`:

- **404** — funcionário não encontrado (`FuncionarioNaoEncontradoException`)
- **400** — dado inválido (ex.: tentar efetivar um funcionário que não é estagiário) ou campo obrigatório vazio/errado (`@Valid`)

## Como rodar

```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`. O console do H2 fica disponível pra você ver o banco em memória.

## Como rodar os testes

```bash
mvn test
```

O que já está coberto: testes do Service (regras de negócio, usando Mockito) e testes do Controller (`@WebMvcTest` + `MockMvc`), cobrindo cadastro, validação de campos obrigatórios e busca por id que não existe.
