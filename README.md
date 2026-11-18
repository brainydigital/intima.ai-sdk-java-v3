<br />
<div align="center">
  <a href="#">
    <img src="https://raw.githubusercontent.com/brainydigital/intima.ai-sdk-php/master/docs/images/logo.png" alt="Logo" width="120" height="120">
  </a>

[![Maven Central](https://img.shields.io/maven-central/v/ai.intima/intimaai-sdk-v3-java.svg?style=flat&color=blue)](https://central.sonatype.com/artifact/ai.intima/intimaai-sdk-v3-java)
[![Contributors](https://img.shields.io/github/contributors/brainydigital/intima.ai-sdk-java-v3.svg?style=flat&color=yellow)](https://github.com/brainydigital/intima.ai-sdk-java-v3/graphs/contributors)
[![MIT license](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

</div>

# Intima.ai — SDK V3 Java

Implementação da API V3 do [Intima.ai](https://app.intima.ai) em forma de SDK Client para Java. Cobre todas as ações disponíveis na plataforma e as expõe como métodos prontos para integração, bastando um `api_token` válido.

- **Versão da API:** 3
- **Versão da SDK:** 3.1.0
- **Referência completa:** [Postman Docs](https://documenter.getpostman.com/view/11707205/2sAYBaAVnT)

## Requisitos

- Java >= 17

## Instalação

### Via Maven

```xml
<dependency>
    <groupId>ai.intima</groupId>
    <artifactId>intimaai-sdk-v3-java</artifactId>
    <version>3.1.0</version>
</dependency>
```

### Via Gradle

```groovy
implementation 'ai.intima:intimaai-sdk-v3-java:3.1.0'
```

## Início Rápido

Antes de executar qualquer ação, você precisa:

1. **Importar um Certificado A1** na sua conta (emitido por qualquer certificadora autorizada), _ou_ cadastrar um autenticador com usuário/senha, caso o tribunal suporte;
2. **Criar uma Autenticação** para cada tribunal onde deseja operar;
3. **Solicitar ações** usando os serviços correspondentes.

### Exemplo: Solicitar cópia processual

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Copia.CopiaSolicitacao;
import ai.intima.Exceptions.APIRequestException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Intimai intimai = new Intimai("seu_api_token");

            String idAutenticador = "1";                        // ID do autenticador no Intima.ai
            String numeroDoProcesso = "00000000000000000000";   // Número do processo no tribunal

            CopiaSolicitacao copia = new CopiaSolicitacao(idAutenticador, numeroDoProcesso);

            Map<String, Object> resultado = intimai.servicos.solicitar(copia);

            System.out.println(resultado);
        } catch (APIRequestException e) {
            System.out.println(e.toJson());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

## Paginação e Filtros

Os serviços de busca suportam paginação e filtros opcionais:

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Copia.CopiaBusca;
import ai.intima.Exceptions.APIRequestException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Intimai intimai = new Intimai("seu_api_token");

            CopiaBusca copias = new CopiaBusca();
            int pagina = 5;
            Map<String, String> filtros = new HashMap<>();
            filtros.put("status", "1,3");      // Filtros com vários valores aceitam "1,2,3"
            filtros.put("per_page", "50");

            Map<String, Object> resultado = intimai.servicos.buscar(copias, pagina, filtros);

            System.out.println(resultado);
        } catch (APIRequestException e) {
            System.out.println(e.toJson());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

O tamanho da página é 15 por padrão. `per_page` muda esse valor nas buscas de ações e de consultas.

## Autenticação em dois fatores (2FA)

Se a conta tiver 2FA ativo, toda request precisa do código de 6 dígitos do app autenticador. A SDK envia o código no header `X-Codigo-2FA`, igual à collection da API V3. O `api_token` continua na query.

```java
import ai.intima.Intimai;
import java.util.Map;

// Código fixo (vale enquanto o app autenticador mostrar o mesmo código)
Intimai intimai = new Intimai("seu_api_token", Map.of("codigo_2fa", "482913"));

// Ou um Supplier chamado a cada request, para sempre mandar o código atual
intimai.definirCodigo2fa(() -> meuGeradorDeCodigo());

// Também dá para trocar o código fixo depois
intimai.definirCodigo2fa("123456");
```

Sem 2FA na conta, não informe nada: o header não é enviado.

As opções do construtor também aceitam `headers` e `params` (mapas somados aos headers e à query padrão) e `url_base`.

## Limites e erros

Quando a API responde com erro, a SDK lança `APIRequestException` com o status e o corpo da resposta:

```java
import ai.intima.Exceptions.APIRequestException;
import ai.intima.Exceptions.DoisFatoresException;
import ai.intima.Exceptions.LimiteDeRequisicoesException;

try {
    Map<String, Object> resultado = intimai.servicos.solicitar(copia);
} catch (DoisFatoresException e) {
    // 401: código 2FA ausente, inválido ou cadastro de 2FA pendente no painel
} catch (LimiteDeRequisicoesException e) {
    Integer espera = e.getRetryAfter();
    Thread.sleep((espera != null ? espera : 60) * 1000L);
} catch (APIRequestException e) {
    e.getStatus();   // código HTTP (null quando nem chegou à API)
    e.getMensagem(); // mensagem da API
    e.getErros();    // erros de validação por campo (422)
    e.getCorpo();    // corpo completo da resposta
}
```

| HTTP | Quando |
| ---- | ------ |
| 401  | `api_token` inválido ou problema no 2FA (`DoisFatoresException`) |
| 403  | Usuário inativo |
| 422  | Erro de validação (`getErros()`), dados fiscais incompletos, autenticador indisponível ou certificado duplicado |
| 429  | Mais de 1000 requests por minuto, ou mais de 10 códigos 2FA errados por minuto (`LimiteDeRequisicoesException`) |
| 457  | Cota de solicitações por hora do plano esgotada |
| 458  | Certificado inválido ou ausente |
| 459  | Saldo insuficiente, ou serviço indisponível no tribunal |

O tamanho máximo dos PDFs e a quantidade de arquivos por protocolo são definidos pela API; se passar do limite, a resposta é 422.

## Serviços Disponíveis

| Serviço               | Recursos disponíveis                                                                                                                                                                                  |
| --------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Atualizar             | Autenticador, Escuta, Intimação, Notificação                                                                                                                                                          |
| Buscar                | Ação, Andamento, Autenticador, Captura Agendada, Certificado, Código de Ajuda, Consulta, Consulta pré-análise, Cópia, Crédito, Escuta, Informação, Intimação, Notificação, Protocolo, Protocolo Habilitação, Transação, Tribunal, Usuário |
| Cadastrar             | Andamento, Autenticador, Autenticador 2FA, Escuta, Notificação                                                                                                                                                          |
| Excluir               | Andamento, Escuta, Notificação                                                                                                                                                                        |
| Resultado             | Ação, Andamento, Consulta, Escuta                                                                                                                                                                     |
| Solicitar             | Ação (confirmar/cancelar consulta), Andamento, Autenticador (capturar intimações), Consulta, Consulta pré-análise, Cópia, Escuta, Informação, Protocolo, Protocolo Habilitação                                                                                                |
| Solicitar Atualização | Certificado                                                                                                                                                                                           |
| Solicitar Cadastro    | Certificado                                                                                                                                                                                           |
| Solicitar Exclusão    | Certificado                                                                                                                                                                                           |
| Tentar Novamente      | Ação                                                                                                                                                                                                  |

## Recursos

| Recurso                                                                                                     | Descrição                                                        |
| ----------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------- |
| [**Ações**](docs/Recursos/acoes.md#recurso-ações)                                                           | Endpoints para ações realizadas no Intima.ai                     |
| [**Andamentos**](docs/Recursos/andamentos.md#recurso-andamentos)                                            | Endpoints para andamentos processuais                            |
| [**Autenticações**](docs/Recursos/autenticacoes.md#recurso-autenticacões)                                   | Endpoints para autenticações                                     |
| [**Capturas Agendadas**](docs/Recursos/capturasAgendadas.md#recurso-capturas-agendadas)                     | Histórico dos horários agendados de captura de intimações e escutas |
| [**Certificados**](docs/Recursos/certificados.md#recurso-certificados)                                      | Endpoints para certificados                                      |
| [**Códigos de Ajuda**](docs/Recursos/codigosAjuda.md#recurso-códigos-de-ajuda)                              | Catálogo de códigos de erro, cobrança e nova tentativa automática |
| [**Consultas**](docs/Recursos/consultas.md#recurso-consultas)                                               | Endpoints para consultas processuais e pré-análises              |
| [**Cópias**](docs/Recursos/copias.md#recurso-cópias)                                                        | Endpoints para cópias processuais                                |
| [**Créditos**](docs/Recursos/creditos.md#recurso-créditos)                                                  | Endpoints para créditos no Intima.ai                             |
| [**Escutas**](docs/Recursos/escutas.md#recurso-escutas)                                                     | Endpoints para escutas processuais                               |
| [**Informações**](docs/Recursos/informacoes.md#recurso-informações)                                         | Endpoints para informações processuais                           |
| [**Intimações**](docs/Recursos/intimacoes.md#recurso-intimações)                                            | Endpoints para intimações capturadas                             |
| [**Notificações**](docs/Recursos/notificacoes.md#recurso-notificações)                                      | Endpoints para notificações do usuário                           |
| [**Protocolos**](docs/Recursos/protocolos.md#recurso-protocolos)                                            | Endpoints para protocolos                                        |
| [**Protocolos de Habilitação**](docs/Recursos/protocolosDeHabilitacao.md#recurso-protocolos-de-habilitação) | Endpoints para protocolos de habilitação                         |
| [**Transações**](docs/Recursos/transacoes.md#recurs-transações)                                             | Endpoints para transações no Intima.ai                           |
| [**Tribunais**](docs/Recursos/tribunais.md#recurso-tribunais)                                               | Endpoints para tribunais                                         |
| [**Usuários**](docs/Recursos/usuarios.md#recurso-usuários)                                                  | Endpoints para o usuário                                         |

## Modelos

| Modelo                                                                       | Descrição                                                                                           |
| ---------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| [**Certificado**](docs/Modelos/certificado.md#modelo-certificado)            | Instanciação do certificado A1 (necessário para cadastro e atualização de certificado no Intima.ai) |
| [**Documento**](docs/Modelos/documento.md#modelo-documento)                  | Instanciação de documento (necessário para protocolos em alguns tribunais)                          |
| [**Parte Vinculada**](docs/Modelos/parteVinculada.md#modelo-parte-vinculada) | Instanciação de parte vinculada (necessário para protocolos em alguns tribunais)                    |
| [**Petição**](docs/Modelos/peticao.md#modelo-petição)                        | Instanciação de petição (necessário para protocolos em alguns tribunais)                            |

## Utilidades

| Utilidade                                                                             | Descrição                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------- |
| [**Ação**](docs/Utilidades/acao.md#utilidade-ação)                                    | Constantes para tipos de ação                           |
| [**Captura Agendada**](docs/Utilidades/capturaAgendada.md#utilidade-captura-agendada) | Constantes para tipos e situações de captura agendada   |
| [**Dia**](docs/Utilidades/dia.md#utilidade-dia)                                       | Constantes para dias da semana                          |
| [**Horário**](docs/Utilidades/horario.md#utilidade-horário)                           | Constantes para horários                                |
| [**Nova Tentativa**](docs/Utilidades/novaTentativa.md#utilidade-nova-tentativa)       | Constantes para a situação da nova tentativa automática |
| [**Polo**](docs/Utilidades/polo.md#utilidade-polo)                                    | Constantes para polo processual (ativo/passivo)         |
| [**Status da Ação**](docs/Utilidades/statusAcao.md#utilidade-status-da-ação)          | Constantes para o status das ações                      |
| [**Tipo Declaração**](docs/Utilidades/tipoDeclaracao.md#utilidade-tipo-declaração)    | Constantes para tipos de declaração                     |
| [**Tipo Solicitação**](docs/Utilidades/tipoSolicitacao.md#utilidade-tipo-solicitação) | Constantes para tipos de solicitação                    |

## Autenticação

| Campo       | Valor            |
| ----------- | ---------------- |
| Tipo        | API Key          |
| Parâmetro   | `api_token`      |
| Localização | URL query string |

Com 2FA ativo na conta, o código vai no header `X-Codigo-2FA` (veja [Autenticação em dois fatores](#autenticação-em-dois-fatores-2fa)).

## Testes

```bash
mvn test
```

## Autores

- **BrainyDigital** — [Intima.ai](https://intima.ai)
