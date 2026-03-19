# Recurso: **Informações**

> Com as informações, você poderá obter todas as informações do processo, tais como:
> valor da causa, classe judicial, assuntos, polos e etc.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## Exemplos

Para solicitar serviços referentes às informações, você deve instanciar o tipo de serviço antes e inserir nas funções padrões dos serviços. Veja os serviços disponíveis para as informações nos exemplos abaixo.

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

### Geral

Exemplo para realizar uma busca geral de todas as informações processuais do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Informacao.InformacaoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os registros daquele serviço
    InformacaoBusca informacoes = new InformacaoBusca();

    Object resultado = intimai.servicos.buscar(informacoes);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Específica

Exemplo para realizar uma busca específica de uma informação processual.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Informacao.InformacaoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idInformacao = 1; // ID da informação no Intima.ai

    InformacaoBusca informacao = new InformacaoBusca(idInformacao);

    Object resultado = intimai.servicos.buscar(informacao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Solicitação**

Exemplo para realizar a solicitação de uma informação processual.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Informacao.InformacaoSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal

    InformacaoSolicitacao informacao = new InformacaoSolicitacao(
        idAutenticador,
        numeroDoProcesso
    );

    Object resultado = intimai.servicos.solicitar(informacao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-informações)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
