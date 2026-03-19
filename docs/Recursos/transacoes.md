# Recurso: **Transações**

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

### Histórico

Exemplo para realizar a busca do histórico de transações do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Transacao.TransacaoHistoricoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    TransacaoHistoricoBusca historico = new TransacaoHistoricoBusca();

    Object resultado = intimai.servicos.buscar(historico);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-transações)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
