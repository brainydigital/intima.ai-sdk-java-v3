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

- **Versão da API:** 3.0.0
- **Referência completa:** [Postman Docs](https://documenter.getpostman.com/view/11707205/2sAYBaAVnT)

## Requisitos

- Java >= 17

## Instalação

### Via Maven

```xml
<dependency>
    <groupId>ai.intima</groupId>
    <artifactId>intimaai-sdk-v3-java</artifactId>
    <version>3.0.0</version>
</dependency>
```

### Via Gradle

```groovy
implementation 'ai.intima:intimaai-sdk-v3-java:3.0.0'
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
            filtros.put("filtro_1", "valor");

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

## Serviços Disponíveis

| Serviço               | Recursos disponíveis                                                                                                                                                                                  |
| --------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Atualizar             | Autenticador, Escuta, Intimação, Notificação                                                                                                                                                          |
| Buscar                | Ação, Andamento, Autenticador, Certificado, Consulta, Consulta pré-análise, Cópia, Crédito, Escuta, Informação, Intimação, Notificação, Protocolo, Protocolo Habilitação, Transação, Tribunal, Usuário |
| Cadastrar             | Andamento, Autenticador, Escuta, Notificação                                                                                                                                                          |
| Excluir               | Andamento, Escuta, Notificação                                                                                                                                                                        |
| Resultado             | Ação, Andamento, Consulta, Escuta                                                                                                                                                                     |
| Solicitar             | Andamento, Consulta, Consulta pré-análise, Cópia, Escuta, Informação, Protocolo, Protocolo Habilitação                                                                                                |
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
| [**Certificados**](docs/Recursos/certificados.md#recurso-certificados)                                      | Endpoints para certificados                                      |
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
| [**Dia**](docs/Utilidades/dia.md#utilidade-dia)                                       | Constantes para dias da semana                          |
| [**Horário**](docs/Utilidades/horario.md#utilidade-horário)                           | Constantes para horários                                |
| [**Polo**](docs/Utilidades/polo.md#utilidade-polo)                                    | Constantes para polo processual (ativo/passivo)         |
| [**Tipo Declaração**](docs/Utilidades/tipoDeclaracao.md#utilidade-tipo-declaração)    | Constantes para tipos de declaração                     |
| [**Tipo Solicitação**](docs/Utilidades/tipoSolicitacao.md#utilidade-tipo-solicitação) | Constantes para tipos de solicitação                    |

## Autenticação

| Campo       | Valor            |
| ----------- | ---------------- |
| Tipo        | API Key          |
| Parâmetro   | `api_token`      |
| Localização | URL query string |
