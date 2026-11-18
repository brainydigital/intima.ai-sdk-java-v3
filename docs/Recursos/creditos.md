# Recurso: **Créditos**

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

### Cotação

Exemplo para realizar a busca da cotação de créditos por ação.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Credito.CreditoCotacaoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    CreditoCotacaoBusca cotacao = new CreditoCotacaoBusca();

    Object resultado = intimai.servicos.buscar(cotacao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Histórico

Exemplo para realizar a busca do histórico de créditos do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Credito.CreditoHistoricoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    CreditoHistoricoBusca historico = new CreditoHistoricoBusca();

    Object resultado = intimai.servicos.buscar(historico);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Saldo

Exemplo para realizar a busca do saldo de créditos do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Credito.CreditoSaldoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    CreditoSaldoBusca saldo = new CreditoSaldoBusca();

    Object resultado = intimai.servicos.buscar(saldo);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Consumo mensal

Exemplo para buscar o consumo de créditos de um mês, agrupado por tipo de ação. O filtro `date` usa o formato `YYYY-MM`; sem ele, a API usa o mês atual.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Credito.CreditoConsumoMensalBusca;
import ai.intima.Exceptions.APIRequestException;
import java.util.Map;

try {
    Intimai intimai = new Intimai("api_token");

    Object resultado = intimai.servicos.buscar(new CreditoConsumoMensalBusca(), 1, Map.of("date", "2026-10"));

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-créditos)
[Voltar a lista da API](../../README.md#recursos)
[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
