# Recurso: **Ações**

> Todas as ações realizadas dentro do `Intima.ai`, ficam registradas neste recurso. Você pode
> buscar (todas ou uma ação específica), buscar o resultado ou tentar novamente.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

### Geral

Exemplo para realizar uma busca geral de todas as ações do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Acao.AcaoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id, serão retornadas todas as ações do usuário
    AcaoBusca acoes = new AcaoBusca();

    Object resultado = intimai.servicos.buscar(acoes);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Específica

Exemplo para realizar uma busca específica de uma ação do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Acao.AcaoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAcao = 1; // ID da ação no Intima.ai

    AcaoBusca acao = new AcaoBusca(idAcao);

    Object resultado = intimai.servicos.buscar(acao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Resultado**

Exemplo para realizar a busca do resultado de uma ação.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Resultado.Acao.AcaoResultado;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAcao = 1; // ID da ação no Intima.ai

    AcaoResultado acao = new AcaoResultado(idAcao);

    Object resultado = intimai.servicos.resultado(acao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Tentar Novamente**

Exemplo para tentar novamente uma ação que tenha falhado.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.TentarNovamente.Acao.AcaoTentaNovamente;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAcao = 1; // ID da ação no Intima.ai

    AcaoTentaNovamente acao = new AcaoTentaNovamente(idAcao);

    Object resultado = intimai.servicos.tentarNovamente(acao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-ações)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
