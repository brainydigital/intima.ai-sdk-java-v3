# Recurso: **Notificações**

> Através deste recurso você poderá ter o controle de todos os outros emails em que se deseja
> receber notificações, além do seu email principal.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## Exemplos

Para solicitar serviços referentes às notificações, você deve instanciar o tipo de serviço antes e inserir nas funções padrões dos serviços. Veja os serviços disponíveis para as notificações nos exemplos abaixo.

## Notificação por Email

### **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

#### Geral

Exemplo para realizar uma busca geral de todas as notificações por email do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Notificacao.NotificacaoEmailBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os
    // registros daquele serviço
    NotificacaoEmailBusca notificacoesEmail = new NotificacaoEmailBusca();

    Object resultado = intimai.servicos.buscar(notificacoesEmail);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Específica

Exemplo para realizar uma busca específica de uma notificação por email.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Notificacao.NotificacaoEmailBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idNotificacaoEmail = 1; // ID da notificação por email no Intima.ai

    NotificacaoEmailBusca notificacaoEmail = new NotificacaoEmailBusca(idNotificacaoEmail);

    Object resultado = intimai.servicos.buscar(notificacaoEmail);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Cadastro**

Exemplo para realizar o cadastro de uma notificação por email.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Cadastrar.Notificacao.NotificacaoEmailCadastro;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String email = "email@exemplo.com";

    NotificacaoEmailCadastro notificacaoEmail = new NotificacaoEmailCadastro(email);

    Object resultado = intimai.servicos.cadastrar(notificacaoEmail);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Atualização**

Exemplo para realizar a atualização de uma notificação por email.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Atualizar.Notificacao.NotificacaoEmailAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idNotificacaoEmail = 1; // ID da notificação por email no Intima.ai
    String novoEmail = "email-novo@exemplo.com";

    NotificacaoEmailAtualizacao notificacaoEmail = new NotificacaoEmailAtualizacao(
        idNotificacaoEmail,
        novoEmail
    );

    Object resultado = intimai.servicos.atualizar(notificacaoEmail);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Exclusão**

Exemplo para realizar a exclusão de uma notificação por email.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Excluir.Notificacao.NotificacaoEmailExclusao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idNotificacaoEmail = 1; // ID da notificação por email no Intima.ai

    NotificacaoEmailExclusao notificacaoEmail = new NotificacaoEmailExclusao(idNotificacaoEmail);

    Object resultado = intimai.servicos.excluir(notificacaoEmail);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

---

## Notificação por Webhook

### **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

#### Geral

Exemplo para realizar uma busca geral de todas as notificações por webhook do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Notificacao.NotificacaoWebhookBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os
    // registros daquele serviço
    NotificacaoWebhookBusca notificacoesWebhook = new NotificacaoWebhookBusca();

    Object resultado = intimai.servicos.buscar(notificacoesWebhook);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Específica

Exemplo para realizar uma busca específica de uma notificação por webhook.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Notificacao.NotificacaoWebhookBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idNotificacaoWebhook = 1; // ID da notificação por webhook no Intima.ai

    NotificacaoWebhookBusca notificacaoWebhook = new NotificacaoWebhookBusca(idNotificacaoWebhook);

    Object resultado = intimai.servicos.buscar(notificacaoWebhook);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Cadastro**

Exemplo para realizar o cadastro de uma notificação por webhook.

```java
import ai.intima.Intimai;
import ai.intima.Utilidades.Acao;
import ai.intima.Servicos.Cadastrar.Notificacao.NotificacaoWebhookCadastro;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int tipoAcao = Acao.TIPO_COPIA;
    String verboHttp = "GET";
    String url = "url-exemplo.com";

    NotificacaoWebhookCadastro notificacaoWebhook = new NotificacaoWebhookCadastro(
        tipoAcao,
        verboHttp,
        url
    );

    Object resultado = intimai.servicos.cadastrar(notificacaoWebhook);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Atualização**

Exemplo para realizar a atualização de uma notificação por webhook.

```java
import ai.intima.Intimai;
import ai.intima.Utilidades.Acao;
import ai.intima.Servicos.Atualizar.Notificacao.NotificacaoWebhookAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idNotificacaoWebhook = 1; // ID da notificação por webhook no Intima.ai
    int novoTipoAcao = Acao.TIPO_INFORMACAO;
    String novoVerboHttp = "POST";
    String novaUrl = "nova-url-exemplo.com";

    NotificacaoWebhookAtualizacao notificacaoWebhook = new NotificacaoWebhookAtualizacao(
        idNotificacaoWebhook,
        novoTipoAcao,
        novoVerboHttp,
        novaUrl
    );

    Object resultado = intimai.servicos.atualizar(notificacaoWebhook);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Exclusão**

Exemplo para realizar a exclusão de uma notificação por webhook.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Excluir.Notificacao.NotificacaoWebhookExclusao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idNotificacaoWebhook = 1; // ID da notificação por webhook no Intima.ai

    NotificacaoWebhookExclusao notificacaoWebhook = new NotificacaoWebhookExclusao(idNotificacaoWebhook);

    Object resultado = intimai.servicos.excluir(notificacaoWebhook);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-notificações)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
