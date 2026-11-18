# Recurso: **Capturas Agendadas**

> Cada horário agendado de captura de intimações ou de escuta processual vira uma linha neste recurso.
> Assim você acompanha o que rodou, o que deu erro e o que não chegou a ser executado (com o motivo).

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

| Método | Rota                                              |
| ------ | ------------------------------------------------- |
| GET    | `/captura-agendada/buscar`                        |
| GET    | `/captura-agendada/{captura_agendada_id}/buscar`  |

## **Busca**

### Geral

Filtros aceitos (todos opcionais; os de lista aceitam `1,2,3`):

| Filtro               | Valores                                                                        |
| -------------------- | ------------------------------------------------------------------------------ |
| `data_inicial`       | data inicial do horário previsto                                               |
| `data_final`         | data final do horário previsto                                                 |
| `autenticadores_ids` | IDs de autenticadores                                                          |
| `escutas_ids`        | IDs de escutas processuais                                                     |
| `tipos`              | `TipoCapturaAgendada.INTIMACAO`, `TipoCapturaAgendada.ESCUTA`                  |
| `situacoes`          | `SituacaoCapturaAgendada.EM_ANDAMENTO`, `FINALIZADA`, `NAO_EXECUTADA`, `ERRO`  |
| `motivos`            | motivo de não execução                                                         |

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.CapturaAgendada.CapturaAgendadaBusca;
import ai.intima.Utilidades.SituacaoCapturaAgendada;
import ai.intima.Utilidades.TipoCapturaAgendada;
import ai.intima.Exceptions.APIRequestException;
import java.util.Map;

try {
    Intimai intimai = new Intimai("api_token");

    Map<String, String> filtros = Map.of(
        "tipos", TipoCapturaAgendada.INTIMACAO,
        "situacoes", SituacaoCapturaAgendada.NAO_EXECUTADA + "," + SituacaoCapturaAgendada.ERRO,
        "autenticadores_ids", "1,2"
    );

    Object resultado = intimai.servicos.buscar(new CapturaAgendadaBusca(), 1, filtros);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
}
```

### Específica

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.CapturaAgendada.CapturaAgendadaBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idCapturaAgendada = "1"; // ID da captura agendada no Intima.ai

    Object resultado = intimai.servicos.buscar(new CapturaAgendadaBusca(idCapturaAgendada));

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
}
```

[Voltar ao topo](#recurso-capturas-agendadas)
[Voltar a lista da API](../../README.md#recursos)
[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
