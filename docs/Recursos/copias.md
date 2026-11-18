# Recurso: **Cópias**

> Neste recurso você poderá visualizar e manter todas as suas cópias processuais.
> Bastando somente informar o tribunal e número do processo.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## Exemplos

Para solicitar serviços referentes às cópias, você deve instanciar o tipo de serviço antes e inserir nas funções padrões dos serviços. Veja os serviços disponíveis para as cópias nos exemplos abaixo.

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

### Geral

Exemplo para realizar uma busca geral de todas as cópias do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Copia.CopiaBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os registros daquele serviço
    CopiaBusca copias = new CopiaBusca();

    Object resultado = intimai.servicos.buscar(copias);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Específica

Exemplo para realizar uma busca específica de uma cópia do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Copia.CopiaBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idCopia = "1"; // ID da cópia no Intima.ai

    CopiaBusca copia = new CopiaBusca(idCopia);

    Object resultado = intimai.servicos.buscar(copia);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Solicitação**

Exemplo para realizar a solicitação de uma cópia.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Copia.CopiaSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idAutenticador = "1"; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal

    CopiaSolicitacao copia = new CopiaSolicitacao(idAutenticador, numeroDoProcesso);

    Object resultado = intimai.servicos.solicitar(copia);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-cópias)
[Voltar a lista da API](../../README.md#recursos)
[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
