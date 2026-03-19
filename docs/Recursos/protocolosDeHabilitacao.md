# Recurso: **Protocolos De Habilitação**

> Assim como os protocolos, você pode realizar protocolos de habilitação em quase todos os tribunais
> disponíveis no Brasil. Sem se preocupar com o tamanho dos arquivos em PDF (nos ajustamos e
> recortamos automaticamente, de acordo com o tribunal selecionado).
>
> O protocolo de habilitação é realizado em duas etapas:
>
> - Primeira etapa: Coleta de informações do processo no qual se deseja solicitar habilitação.
> - Segunda etapa: Realização do protocolo, a partir das informações coletadas na etapa anterior.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

---

## Exemplos

Para solicitar serviços referentes aos protocolos de habilitação, você deve instanciar o tipo de serviço antes e inserir nas funções padrões dos serviços. Veja os serviços disponíveis para os protocolos de habilitação nos exemplos abaixo.

---

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros às buscas dos serviços.

### Geral

Exemplo para realizar uma busca geral de todos os protocolos de habilitação do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.ProtocoloHabilitacao.ProtocoloHabilitacaoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço de busca, ele retornará todos os registros daquele serviço
    ProtocoloHabilitacaoBusca protocolosHabilitacao = new ProtocoloHabilitacaoBusca();

    Object resultado = intimai.servicos.buscar(protocolosHabilitacao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

---

### Específica

Exemplo para realizar uma busca específica de um protocolo de habilitação.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.ProtocoloHabilitacao.ProtocoloHabilitacaoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idProtocoloHabilitacao = 1; // ID do protocolo de habilitação no Intima.ai

    ProtocoloHabilitacaoBusca protocoloHabilitacao = new ProtocoloHabilitacaoBusca(idProtocoloHabilitacao);

    Object resultado = intimai.servicos.buscar(protocoloHabilitacao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

---

## **Solicitação**

### Primeira etapa

Para realizar a primeira etapa do protocolo de habilitação, basta informar o número do processo e
o id do autenticador (contendo o tribunal referente ao número do processo).

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.ProtocoloHabilitacao.ProtocoloHabilitacaoPjePrimeiroPassoSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String numeroDoProcesso = "00000000000000000000"; // Número do processo no tribunal

    ProtocoloHabilitacaoPjePrimeiroPassoSolicitacao primeiraEtapa = new ProtocoloHabilitacaoPjePrimeiroPassoSolicitacao(
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

---

### Segunda etapa

Após a finalização da primeira etapa, você terá que informar todas as informações principais do
protocolo de habilitação.

Após a finalização da segunda etapa, a ação do protocolo de habilitação estará finalizada e
você poderá ver a certidão do protocolo que contém todas as informações e serve como
comprovante de protocolo.

```java
import ai.intima.Intimai;
import ai.intima.Utilidades.TipoSolicitacao;
import ai.intima.Utilidades.TipoDeclaracao;
import ai.intima.Utilidades.Polo;
import ai.intima.Modelos.ParteVinculada;
import ai.intima.Modelos.Documento;
import ai.intima.Servicos.Solicitar.ProtocoloHabilitacao.ProtocoloHabilitacaoPjeSegundoPassoSolicitacao;
import ai.intima.Exceptions.APIRequestException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    int idProtocoloHabilitacao = 1; // ID do protocolo de habilitação no Intima.ai

    // Estes tipos estão disponíveis no enum TipoSolicitacao
    int tipoSolicitacao = TipoSolicitacao.HABILITACAO_SIMPLES;

    // Estes tipos estão disponíveis no enum TipoDeclaracao
    int tipoDeclaracao = TipoDeclaracao.DECLARACAO_SOB_PENA_DE_LEI;

    // Estes polos estão disponíveis no enum Polo
    int polo = Polo.ATIVO;

    // Array com as partes. Obs: a parte pode ter participação, verifique no tribunal
    List<ParteVinculada> partesVinculadas = Arrays.asList(new ParteVinculada("nome-parte"));

    // Estes tipos estão disponíveis no protocolo; você pode buscar o protocolo e verificar os valores disponíveis.
    int tipoDocumentoMensagemGeral = 1;

    String descricao = "Descrição da solicitação";
    String mensagemGeral = "Mensagem geral do protocolo";

    List<Documento> documentos = Arrays.asList(
        new Documento(Path.of("caminho/do/documento"), 1, 21, "descricao-doc")
    );

    ProtocoloHabilitacaoPjeSegundoPassoSolicitacao segundaEtapa = new ProtocoloHabilitacaoPjeSegundoPassoSolicitacao(
        idProtocoloHabilitacao,
        tipoSolicitacao,
        tipoDeclaracao,
        polo,
        partesVinculadas,
        tipoDocumentoMensagemGeral,
        descricao,
        mensagemGeral,
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

---

[Voltar ao topo](#recurso-protocolos-de-habilitação)
[Voltar à lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
