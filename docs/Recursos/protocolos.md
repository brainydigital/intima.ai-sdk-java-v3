# Recurso: **Protocolos**

> Você pode realizar protocolos em quase todos os tribunais disponíveis no Brasil. De
> forma rápida e segura, sem se preocupar com tamanho dos arquivos em PDF (nos ajustamos e
> recortamos automaticamente, de acordo com tribunal selecionado).
>
> Para os `protocolos no PJE ou PROJUDI`, tudo é realizado em apenas uma `única etapa`, já para os `protocolos no
> ESAJ, EPROC e TJRJ`, são necessárias `duas etapas` para conclusão do mesmo.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## Exemplos

Para solicitar serviços referentes aos protocolos, você deve instanciar o tipo de serviço antes e inserir nas funções padrões dos serviços. Veja os serviços disponíveis para os protocolos nos exemplos abaixo.

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

### Geral

Exemplo para realizar uma busca geral de todos os protocolos do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Protocolo.ProtocoloBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os registros daquele serviço
    ProtocoloBusca protocolos = new ProtocoloBusca();

    Object resultado = intimai.servicos.buscar(protocolos);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### Específica

Exemplo para realizar uma busca específica de um protocolo.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Protocolo.ProtocoloBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idProtocolo = 1; // ID do protocolo no Intima.ai

    ProtocoloBusca protocolo = new ProtocoloBusca(idProtocolo);

    Object resultado = intimai.servicos.buscar(protocolo);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Solicitação**

### PJE

Protocolos no sistema PJE são realizados em uma única etapa.

```java
import ai.intima.Intimai;
import ai.intima.Modelos.Peticao;
import ai.intima.Modelos.Documento;
import ai.intima.Servicos.Solicitar.Protocolo.ProtocoloPjeSolicitacao;
import ai.intima.Exceptions.APIRequestException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal

    // Estes tipos estão disponíveis no tribunal em questão, você pode buscar o tribunal e verificar os valores disponíveis.
    int tipoDocumentoMensagemGeral = 1;

    // A petição é necessária para alguns tribunais (TRT por exemplo). Obs: você pode buscar os tipos disponíveis da petição ao buscar o tribunal em que se está protocolando
    Peticao peticao = new Peticao(Path.of("caminho/da/peticao"), 12, "descricao-peticao");

    List<Documento> documentos = Arrays.asList(
        new Documento(Path.of("caminho/do/documento"), 1, 21, "descricao-doc")
    );

    // Obs: se o tribunal não precisar da petição basta não enviar
    ProtocoloPjeSolicitacao protocolo = new ProtocoloPjeSolicitacao(
        idAutenticador,
        numeroDoProcesso,
        tipoDocumentoMensagemGeral,
        peticao,
        documentos
    );

    Object resultado = intimai.servicos.solicitar(protocolo);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### PROJUDI

Protocolos no sistema PROJUDI são realizados em uma única etapa.

```java
import ai.intima.Intimai;
import ai.intima.Modelos.Documento;
import ai.intima.Servicos.Solicitar.Protocolo.ProtocoloProjudiSolicitacao;
import ai.intima.Exceptions.APIRequestException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal

    // Estes tipos estão disponíveis no tribunal em questão, você pode buscar o tribunal e verificar os valores disponíveis.
    int tipoDocumentoMensagemGeral = 1;

    List<Documento> documentos = Arrays.asList(
        new Documento(Path.of("caminho/do/documento"), 1, 21, "descricao-doc")
    );

    ProtocoloProjudiSolicitacao protocolo = new ProtocoloProjudiSolicitacao(
        idAutenticador,
        numeroDoProcesso,
        tipoDocumentoMensagemGeral,
        documentos
    );

    Object resultado = intimai.servicos.solicitar(protocolo);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### ESAJ

Protocolos no sistema ESAJ são realizados em duas etapas.

#### Primeira Etapa

Para realizar a primeira etapa do protocolo no sistema ESAJ, basta informar o número do processo e
o id do autenticador (contendo o tribunal referente ao número do processo).

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Protocolo.ProtocoloEsajPrimeiroPassoSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal

    ProtocoloEsajPrimeiroPassoSolicitacao primeiraEtapa = new ProtocoloEsajPrimeiroPassoSolicitacao(
        idAutenticador,
        numeroDoProcesso
    );

    Object resultado = intimai.servicos.solicitar(primeiraEtapa);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Segunda Etapa

Após a finalização da primeira etapa, você terá que informar todas as informações principais do
protocolo.

Após a finalização da segunda etapa, a ação do protocolo estará finalizada e
você poderá ver a certidão do protocolo que contém todas as informações e serve como
comprovante de protocolo.

```java
import ai.intima.Intimai;
import ai.intima.Modelos.ParteVinculada;
import ai.intima.Modelos.Peticao;
import ai.intima.Modelos.Documento;
import ai.intima.Servicos.Solicitar.Protocolo.ProtocoloEsajSegundoPassoSolicitacao;
import ai.intima.Exceptions.APIRequestException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    int idProtocolo = 1; // ID do protocolo no Intima.ai

    // O id da classe pode ser obtido verificando as classes disponíveis ao buscar todas as classes
    int idClasse = 1;

    // O id da categoria pode ser obtido verificando as classes e categorias disponíveis ao buscar todas as classes
    int idCategoria = 1;

    // Array com as partes. Obs: a parte pode ter participação, verifique no tribunal
    List<ParteVinculada> partesVinculadas = Arrays.asList(new ParteVinculada("nome-parte"));

    Peticao peticao = new Peticao(Path.of("caminho/da/peticao"), 12, "descricao-peticao");

    List<Documento> documentos = Arrays.asList(
        new Documento(Path.of("caminho/do/documento"), 1, 21, "descricao-doc")
    );

    ProtocoloEsajSegundoPassoSolicitacao segundaEtapa = new ProtocoloEsajSegundoPassoSolicitacao(
        idProtocolo,
        idClasse,
        idCategoria,
        partesVinculadas,
        peticao,
        documentos
    );

    Object resultado = intimai.servicos.solicitar(segundaEtapa);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### EPROC

Protocolos no sistema EPROC são realizados em duas etapas.

#### Primeira Etapa

Para realizar a primeira etapa do protocolo no sistema EPROC, basta informar o número do processo e
o id do autenticador (contendo o tribunal referente ao número do processo).

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Protocolo.ProtocoloEprocPrimeiroPassoSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal

    ProtocoloEprocPrimeiroPassoSolicitacao primeiraEtapa = new ProtocoloEprocPrimeiroPassoSolicitacao(
        idAutenticador,
        numeroDoProcesso
    );

    Object resultado = intimai.servicos.solicitar(primeiraEtapa);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Segunda Etapa

Após a finalização da primeira etapa, você terá que informar todas as informações principais do
protocolo.

Após a finalização da segunda etapa, a ação do protocolo estará finalizada e
você poderá ver a certidão do protocolo que contém todas as informações e serve como
comprovante de protocolo.

```java
import ai.intima.Intimai;
import ai.intima.Modelos.ParteVinculada;
import ai.intima.Modelos.Documento;
import ai.intima.Servicos.Solicitar.Protocolo.ProtocoloEprocSegundoPassoSolicitacao;
import ai.intima.Exceptions.APIRequestException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    int idProtocolo = 1; // ID do protocolo no Intima.ai

    // Estes tipos estão disponíveis no protocolo, você pode buscar o protocolo e verificar os valores disponíveis.
    int tipoDocumentoMensagemGeral = 1;

    // Array com as partes. Obs: a parte pode ter participação, verifique no tribunal
    List<ParteVinculada> partesVinculadas = Arrays.asList(new ParteVinculada("nome-parte"));

    List<Documento> documentos = Arrays.asList(
        new Documento(Path.of("caminho/do/documento"), 1, 21, "descricao-doc")
    );

    ProtocoloEprocSegundoPassoSolicitacao segundaEtapa = new ProtocoloEprocSegundoPassoSolicitacao(
        idProtocolo,
        tipoDocumentoMensagemGeral,
        partesVinculadas,
        documentos
    );

    Object resultado = intimai.servicos.solicitar(segundaEtapa);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### TJRJ

Protocolos no sistema TJRJ são realizados em duas etapas.

#### Primeira Etapa

Para realizar a primeira etapa do protocolo no sistema TJRJ, basta informar o número do processo e
o id do autenticador (contendo o tribunal referente ao número do processo).

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Protocolo.ProtocoloTjrjPrimeiroPassoSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal

    ProtocoloTjrjPrimeiroPassoSolicitacao primeiraEtapa = new ProtocoloTjrjPrimeiroPassoSolicitacao(
        idAutenticador,
        numeroDoProcesso
    );

    Object resultado = intimai.servicos.solicitar(primeiraEtapa);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Segunda Etapa

Após a finalização da primeira etapa, você terá que informar todas as informações principais do
protocolo.

Após a finalização da segunda etapa, a ação do protocolo estará finalizada e
você poderá ver a certidão do protocolo que contém todas as informações e serve como
comprovante de protocolo.

```java
import ai.intima.Intimai;
import ai.intima.Modelos.ParteVinculada;
import ai.intima.Modelos.Peticao;
import ai.intima.Modelos.Documento;
import ai.intima.Servicos.Solicitar.Protocolo.ProtocoloTjrjSegundoPassoSolicitacao;
import ai.intima.Exceptions.APIRequestException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    int idProtocolo = 1; // ID do protocolo no Intima.ai

    // O id da classe pode ser obtido verificando as classes disponíveis ao buscar todas as classes
    int idClasse = 1;

    // Array com as partes. Obs: a parte pode ter participação, verifique no tribunal
    List<ParteVinculada> partesVinculadas = Arrays.asList(new ParteVinculada("nome-parte"));

    Peticao peticao = new Peticao(Path.of("caminho/da/peticao"), 12, "descricao-peticao");

    List<Documento> documentos = Arrays.asList(
        new Documento(Path.of("caminho/do/documento"), 1, 21, "descricao-doc")
    );

    ProtocoloTjrjSegundoPassoSolicitacao segundaEtapa = new ProtocoloTjrjSegundoPassoSolicitacao(
        idProtocolo,
        idClasse,
        partesVinculadas,
        peticao,
        documentos
    );

    Object resultado = intimai.servicos.solicitar(segundaEtapa);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-protocolos)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
