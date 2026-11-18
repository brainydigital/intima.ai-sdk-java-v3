# Recurso: **Ações**

> Todas as ações realizadas dentro do `Intima.ai`, ficam registradas neste recurso. Você pode
> buscar (todas ou uma ação específica), buscar o resultado ou tentar novamente.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços. Nesta busca,
> `per_page` muda o tamanho da página (padrão 15). Os valores de `status` estão em [StatusAcao](../Utilidades/statusAcao.md#utilidade-status-da-ação).

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

    String idAcao = "1"; // ID da ação no Intima.ai

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

    String idAcao = "1"; // ID da ação no Intima.ai

    AcaoResultado acao = new AcaoResultado(idAcao);

    Object resultado = intimai.servicos.resultado(acao);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

## **Nova tentativa automática**

A ação com erro (`StatusAcao.ERRO`) traz o bloco `nova_tentativa`; nos outros status ele vem `null`. Use-o antes de criar outra ação: se `automatica` for `true`, a plataforma repete a ação sozinha e não é preciso criar outra. As regras de cada código estão no recurso [Códigos de Ajuda](codigosAjuda.md#recurso-códigos-de-ajuda).

| Campo            | Descrição                                                                                     |
| ---------------- | --------------------------------------------------------------------------------------------- |
| `automatica`     | `true` quando a plataforma ainda vai repetir a ação                                            |
| `situacao`       | `SituacaoNovaTentativa.AGENDADA`, `AGUARDANDO_CONDICAO`, `ENCERRADA` ou `SEM_NOVA_TENTATIVA`  |
| `tentativa`      | Número da próxima tentativa (quando agendada)                                                  |
| `max_tentativas` | Total de tentativas da regra                                                                   |
| `prevista_para`  | Data e hora previstas da próxima tentativa                                                     |
| `mensagem`       | Texto pronto para mostrar ao usuário                                                           |

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Acao.AcaoBusca;
import ai.intima.Utilidades.SituacaoNovaTentativa;
import ai.intima.Exceptions.APIRequestException;
import java.util.Map;

try {
    Intimai intimai = new Intimai("api_token");

    Map<String, Object> acao = intimai.servicos.buscar(new AcaoBusca("1"));
    Map<?, ?> dados = (Map<?, ?>) acao.get("data");
    Map<?, ?> novaTentativa = (Map<?, ?>) dados.get("nova_tentativa");

    if (novaTentativa == null) {
        // A ação não está com erro
    } else if (Boolean.TRUE.equals(novaTentativa.get("automatica"))) {
        // A plataforma vai repetir sozinha: não crie outra ação
        System.out.println(novaTentativa.get("mensagem"));
    } else if (SituacaoNovaTentativa.SEM_NOVA_TENTATIVA.equals(novaTentativa.get("situacao"))) {
        // Corrija a causa do erro antes de criar outra ação
    }
} catch (APIRequestException e) {
    System.out.println(e.getStatus() + " " + e.getMensagem());
}
```

## **Confirmar ou cancelar consulta**

Toda consulta processual por perfil passa por uma pré-análise. Quando ela encontra processos, a consulta para em `StatusAcao.AGUARDANDO_CONFIRMACAO` (13) e a resposta da ação traz o bloco `confirmacao` (total de processos, custo estimado e `expira_em`). A confirmação vale por 1 hora. Cancelar leva a consulta a `StatusAcao.CANCELADA` (14).

| Método | Rota                                  |
| ------ | ------------------------------------- |
| POST   | `/acao/{acao_id}/confirmar-consulta`  |
| POST   | `/acao/{acao_id}/cancelar-consulta`   |

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Acao.AcaoBusca;
import ai.intima.Servicos.Solicitar.Acao.AcaoConfirmarConsultaSolicitacao;
import ai.intima.Servicos.Solicitar.Acao.AcaoCancelarConsultaSolicitacao;
import ai.intima.Utilidades.StatusAcao;
import ai.intima.Exceptions.APIRequestException;
import java.util.Map;

try {
    Intimai intimai = new Intimai("api_token");

    String idAcao = "1"; // ID da consulta (ação) no Intima.ai

    Map<String, Object> consulta = intimai.servicos.buscar(new AcaoBusca(idAcao));
    Map<?, ?> dados = (Map<?, ?>) consulta.get("data");

    if (Integer.valueOf(StatusAcao.AGUARDANDO_CONFIRMACAO).equals(dados.get("status"))) {
        Object confirmacao = dados.get("confirmacao"); // total, custo_estimado, expira_em...

        Map<String, Object> resultado = intimai.servicos.solicitar(new AcaoConfirmarConsultaSolicitacao(idAcao));
        // ou: intimai.servicos.solicitar(new AcaoCancelarConsultaSolicitacao(idAcao));
    }
} catch (APIRequestException e) {
    System.out.println(e.getStatus() + " " + e.getMensagem());
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

    String idAcao = "1"; // ID da ação no Intima.ai

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
[Voltar a lista da API](../../README.md#recursos)
[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
