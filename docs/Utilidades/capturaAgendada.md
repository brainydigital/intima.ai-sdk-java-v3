# Utilidade: **Captura Agendada**

Constantes para os filtros `tipos` e `situacoes` da [busca de capturas agendadas](../Recursos/capturasAgendadas.md#recurso-capturas-agendadas).

```java
import ai.intima.Utilidades.SituacaoCapturaAgendada;
import ai.intima.Utilidades.TipoCapturaAgendada;

String tipo = TipoCapturaAgendada.ESCUTA;
String situacoes = SituacaoCapturaAgendada.FINALIZADA + "," + SituacaoCapturaAgendada.ERRO;
```

| Constante                              | Valor         |
| -------------------------------------- | ------------- |
| TipoCapturaAgendada.INTIMACAO          | intimacao     |
| TipoCapturaAgendada.ESCUTA             | escuta        |
| SituacaoCapturaAgendada.EM_ANDAMENTO   | em_andamento  |
| SituacaoCapturaAgendada.FINALIZADA     | finalizada    |
| SituacaoCapturaAgendada.NAO_EXECUTADA  | nao_executada |
| SituacaoCapturaAgendada.ERRO           | erro          |

[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
