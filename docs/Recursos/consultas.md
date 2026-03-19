# Recurso: **Consultas**

> Com este recurso você poderá realizar consultas de todos os processos disponíveis em qualquer
> tribunal do Brasil. Bastando apenas informar o `numero do processo` ou o `nome da parte` ou o `nome do representante`.
>
> O `Intima.ai` recomenda que antes de realizar qualquer consulta processual, primeiro solicite
> a `pré-análise da consulta`, pois dependendo dos termos utilizados na consulta,
> poderá haver um número muito alto de registros encontrados, consumindo assim seus créditos.
>
> A `pré-análise de uma consulta` retorna a quantidade de registros encontrados com os
> termos de busca informados.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

### Geral

Exemplo para realizar uma busca geral de todas as consultas do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Consulta.ConsultaBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os registros daquele serviço
    ConsultaBusca consultas = new ConsultaBusca();

    Object resultado = intimai.servicos.buscar(consultas);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Específica

Exemplo para realizar uma busca específica de uma consulta do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Consulta.ConsultaBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idConsulta = 1; // ID da consulta no Intima.ai

    ConsultaBusca consulta = new ConsultaBusca(idConsulta);

    Object resultado = intimai.servicos.buscar(consulta);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Solicitar**

Exemplo para realizar a solicitação de uma consulta.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Consulta.ConsultaSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal
    // OU nome do representante >> String nomeRepresentante = "nome-representante";

    ConsultaSolicitacao consulta = new ConsultaSolicitacao(idAutenticador, numeroDoProcesso);

    Object resultado = intimai.servicos.solicitar(consulta);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Resultado**

Exemplo para realizar a busca do resultado de uma consulta.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Resultado.Consulta.ConsultaResultado;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idConsulta = 1; // ID da consulta no Intima.ai

    ConsultaResultado consulta = new ConsultaResultado(idConsulta);

    Object resultado = intimai.servicos.resultado(consulta);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

---

## **Consulta Pré-Análise**

> A pré-análise retorna a quantidade de registros encontrados com os termos de busca informados,
> permitindo que você decida se vale a pena realizar a consulta completa antes de consumir créditos.

### **Busca de Pré-Análise**

#### Geral

Exemplo para realizar uma busca geral de todas as consultas de pré-análise do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.ConsultaPreAnalise.ConsultaPreAnaliseBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os registros daquele serviço
    ConsultaPreAnaliseBusca consultasPreAnalise = new ConsultaPreAnaliseBusca();

    Object resultado = intimai.servicos.buscar(consultasPreAnalise);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Específica

Exemplo para realizar uma busca específica de uma consulta de pré-análise.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.ConsultaPreAnalise.ConsultaPreAnaliseBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idConsultaPreAnalise = 1; // ID da consulta pré-análise no Intima.ai

    ConsultaPreAnaliseBusca consultaPreAnalise = new ConsultaPreAnaliseBusca(idConsultaPreAnalise);

    Object resultado = intimai.servicos.buscar(consultaPreAnalise);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Solicitar Pré-Análise**

Exemplo para realizar a solicitação de uma pré-análise de consulta.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.ConsultaPreAnalise.ConsultaPreAnaliseSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal

    ConsultaPreAnaliseSolicitacao consultaPreAnalise = new ConsultaPreAnaliseSolicitacao(idAutenticador, numeroDoProcesso);

    Object resultado = intimai.servicos.solicitar(consultaPreAnalise);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-consultas)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
