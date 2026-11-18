# Recurso: **Intimações**

> A captura das intimações ocorrem em determinados horários e dias escolhidos por você, ao se adicionar um tribunal em `Tribunais ativos`.
>
> Para cada "Tribunal ativo" na sua lista de Tribunais ativos, você pode ou não habilitar a
> captura de intimações. Caso a captura de intimações esteja habilitada para o tribunal selecionado,
> será necessário informar os dias da semana em que ocorrerão as capturas, bem como ao menos um
> horário para captura e também quais as abas que deseja que o `INTIMA.AI` realize a captura das
> intimações.
>
> Após finalizada a ação de captura de intimações, será possível verificar todos os documentos
> atrelados às intimações (em texto ou em PDF), bem como realizar a revisão das intimações.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## Exemplos

Para solicitar serviços referentes às intimações, você deve instanciar o tipo de serviço antes e inserir nas funções padrões dos serviços. Veja os serviços disponíveis para as intimações nos exemplos abaixo.

### **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

#### Geral

Exemplo para realizar uma busca geral de todas as intimações do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Intimacao.IntimacaoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os registros daquele serviço
    IntimacaoBusca intimacoes = new IntimacaoBusca();

    Object resultado = intimai.servicos.buscar(intimacoes);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Específica

Exemplo para realizar uma busca específica de uma intimação.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Intimacao.IntimacaoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idIntimacao = "1"; // ID da intimação no Intima.ai

    IntimacaoBusca intimacao = new IntimacaoBusca(idIntimacao);

    Object resultado = intimai.servicos.buscar(intimacao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Atualização**

#### **Marcar como revisada**

> Você pode marcar a intimação como revisada para melhor organização.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Atualizar.Intimacao.IntimacaoMarcarComoRevisadaAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idIntimacao = "1"; // ID da intimação no Intima.ai

    IntimacaoMarcarComoRevisadaAtualizacao intimacao = new IntimacaoMarcarComoRevisadaAtualizacao(idIntimacao);

    Object resultado = intimai.servicos.atualizar(intimacao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-intimações)
[Voltar a lista da API](../../README.md#recursos)
[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
