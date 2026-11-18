# Recurso: **Escutas**

> Com a escuta você pode saber todas as movimentações que ocorreram em um processo,
> e baixar todos os novos documentos anexados ao processo. Para realizá-la
> é bastante simples, bastando somente informar o número do processo, tribunal e os dias/horários que
> deseja realizar.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

### Geral

Exemplo para realizar uma busca geral de todas as escutas cadastradas do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Escuta.EscutaBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os registros daquele serviço
    EscutaBusca escutas = new EscutaBusca();

    Object resultado = intimai.servicos.buscar(escutas);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Específica

Exemplo para realizar uma busca específica de uma escuta.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Escuta.EscutaBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idEscuta = "1"; // ID da escuta no Intima.ai

    EscutaBusca escuta = new EscutaBusca(idEscuta);

    Object resultado = intimai.servicos.buscar(escuta);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Cadastrar**

Exemplo para realizar o cadastro de uma escuta.

```java
import ai.intima.Intimai;
import ai.intima.Utilidades.Dia;
import ai.intima.Utilidades.Horario;
import ai.intima.Servicos.Cadastrar.Escuta.EscutaCadastro;
import ai.intima.Exceptions.APIRequestException;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    // ID do autenticador no Intima.ai
    String idAutenticador = "1";

    // Número do processo no tribunal
    String numeroDoProcesso = "00000000000000000000";

    // Dias no qual a captura será realizada
    List<Integer> diasDeCaptura = Arrays.asList(Dia.SEGUNDA_FEIRA, Dia.SEXTA_FEIRA);

    // Horários no qual a captura será realizada (no máximo 3, de 01:00 a 23:00)
    List<String> horariosDeCaptura = Arrays.asList(Horario.DEZ_HORAS, Horario.DEZOITO_HORAS);

    EscutaCadastro escuta = new EscutaCadastro(
        idAutenticador,
        numeroDoProcesso,
        diasDeCaptura,
        horariosDeCaptura
    );

    Object resultado = intimai.servicos.cadastrar(escuta);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Solicitar**

Exemplo para realizar a solicitação de captura de uma escuta.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Escuta.EscutaSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idEscuta = "1"; // ID da escuta no Intima.ai

    EscutaSolicitacao escuta = new EscutaSolicitacao(idEscuta);

    Object resultado = intimai.servicos.solicitar(escuta);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Resultado**

Exemplo para realizar a busca do resultado de uma escuta.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Resultado.Escuta.EscutaResultado;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idEscuta = "1"; // ID da escuta no Intima.ai

    EscutaResultado escuta = new EscutaResultado(idEscuta);

    Object resultado = intimai.servicos.resultado(escuta);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Atualizar**

Exemplo para realizar a atualização de uma escuta.

```java
import ai.intima.Intimai;
import ai.intima.Utilidades.Dia;
import ai.intima.Utilidades.Horario;
import ai.intima.Servicos.Atualizar.Escuta.EscutaAtualizacao;
import ai.intima.Exceptions.APIRequestException;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    String idEscuta = "1"; // ID da escuta no Intima.ai

    // Dias no qual a captura será realizada
    List<Integer> diasDeCaptura = Arrays.asList(Dia.SEGUNDA_FEIRA, Dia.SEXTA_FEIRA);

    // Horários no qual a captura será realizada (no máximo 3, de 01:00 a 23:00)
    List<String> horariosDeCaptura = Arrays.asList(Horario.DEZ_HORAS, Horario.DEZOITO_HORAS);

    EscutaAtualizacao escuta = new EscutaAtualizacao(
        idEscuta,
        diasDeCaptura,
        horariosDeCaptura
    );

    Object resultado = intimai.servicos.atualizar(escuta);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Excluir**

Exemplo para realizar a exclusão de uma escuta.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Excluir.Escuta.EscutaExclusao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idEscuta = "1"; // ID da escuta no Intima.ai

    EscutaExclusao escuta = new EscutaExclusao(idEscuta);

    Object resultado = intimai.servicos.excluir(escuta);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-escutas)
[Voltar a lista da API](../../README.md#recursos)
[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
