# Recurso: **Andamentos**

> Com os Andamentos, você poderá visualizar todas as movimentações e documentos
> (PDF e HTML) do polo interno de um processo.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

### Geral

Exemplo para realizar uma busca geral de todos os andamentos cadastrados do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Andamento.AndamentoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id, serão retornados todos os andamentos do usuário
    AndamentoBusca andamentos = new AndamentoBusca();

    Object resultado = intimai.servicos.buscar(andamentos);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Específica

Exemplo para realizar uma busca específica de um andamento cadastrado do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Andamento.AndamentoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAndamento = 1; // ID do andamento no Intima.ai

    AndamentoBusca andamento = new AndamentoBusca(idAndamento);

    Object resultado = intimai.servicos.buscar(andamento);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Cadastrar**

Exemplo para realizar o cadastro de um andamento.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Cadastrar.Andamento.AndamentoCadastro;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1;
    String numeroProcesso = "00000000000000000000";

    AndamentoCadastro andamento = new AndamentoCadastro(idAutenticador, numeroProcesso);

    Object resultado = intimai.servicos.cadastrar(andamento);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Solicitar**

Exemplo para realizar a captura de um andamento.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Andamento.AndamentoSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAndamento = 1;

    AndamentoSolicitacao andamento = new AndamentoSolicitacao(idAndamento);

    Object resultado = intimai.servicos.solicitar(andamento);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Resultado**

Exemplo para realizar a busca do resultado de um andamento.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Resultado.Andamento.AndamentoResultado;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAndamento = 1; // ID do andamento no Intima.ai

    AndamentoResultado andamento = new AndamentoResultado(idAndamento);

    Object resultado = intimai.servicos.resultado(andamento);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Excluir**

Exemplo para realizar a exclusão de um andamento.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Excluir.Andamento.AndamentoExclusao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int id = 1;

    AndamentoExclusao andamento = new AndamentoExclusao(id);

    Object resultado = intimai.servicos.excluir(andamento);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-andamentos)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
