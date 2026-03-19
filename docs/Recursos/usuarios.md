# Recurso: **Usuários**

> Através deste recurso você pode consultar todas as informações do usuário.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## Exemplos

Para solicitar serviços referentes ao usuário, você deve instanciar o tipo de serviço antes e inserir nas funções padrões dos serviços. Veja os serviços disponíveis para o usuário nos exemplos abaixo.

### **Busca**

Exemplo para realizar a busca do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Usuario.UsuarioBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    UsuarioBusca usuario = new UsuarioBusca();

    Object resultado = intimai.servicos.buscar(usuario);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-usuários)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
