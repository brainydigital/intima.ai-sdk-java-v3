# Recurso: **Certificados**

> Certas ações como protocolar, por exemplo, exigem que possua um `Tribunal` com o tipo de autenticação
> `Certificado A1`.
>
> Após associar um `Certificado A1` a sua conta, você deverá esperar até que o mesmo seja aprovado e
> liberado por nossa equipe.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## Exemplos

Para solicitar serviços referentes ao certificado, você deve instanciar o tipo de serviço antes e inserir nas funções padrões dos serviços. Veja os serviços disponíveis para o certificado nos exemplos abaixo.

### **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

#### Geral

Exemplo para realizar uma busca geral de todos os certificados do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Certificado.CertificadoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os registros daquele serviço
    CertificadoBusca certificados = new CertificadoBusca();

    Object resultado = intimai.servicos.buscar(certificados);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Específica

Exemplo para realizar uma busca específica de um certificado do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Certificado.CertificadoBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idCertificado = 1; // ID do certificado no Intima.ai

    CertificadoBusca certificado = new CertificadoBusca(idCertificado);

    Object resultado = intimai.servicos.buscar(certificado);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Solicitações**

Exemplos dos serviços disponíveis para solicitação do cadastro, atualização ou exclusão do certificado.

#### Cadastro

Exemplo para solicitar o cadastro de um certificado.

```java
import ai.intima.Intimai;
import ai.intima.Modelos.Certificado;
import ai.intima.Servicos.SolicitarCadastro.Certificado.CertificadoSolicitacaoCadastro;
import ai.intima.Exceptions.APIRequestException;
import java.nio.file.Path;

try {
    Intimai intimai = new Intimai("api_token");

    Certificado modelCertificado = new Certificado(Path.of("caminho/do/certificado"), "senha");

    CertificadoSolicitacaoCadastro certificado = new CertificadoSolicitacaoCadastro(modelCertificado);

    Object resultado = intimai.servicos.solicitarCadastro(certificado);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Atualização

Exemplo para solicitar a atualização de um certificado.

```java
import ai.intima.Intimai;
import ai.intima.Modelos.Certificado;
import ai.intima.Servicos.SolicitarAtualizacao.Certificado.CertificadoSolicitaAtualizacao;
import ai.intima.Exceptions.APIRequestException;
import java.nio.file.Path;

try {
    Intimai intimai = new Intimai("api_token");

    int idCertificado = 1; // ID do certificado no Intima.ai

    Certificado modelCertificado = new Certificado(Path.of("caminho/do/certificado"), "senha");

    CertificadoSolicitaAtualizacao certificado = new CertificadoSolicitaAtualizacao(
        idCertificado,
        modelCertificado
    );

    Object resultado = intimai.servicos.solicitarAtualizacao(certificado);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Exclusão

Exemplo para solicitar a exclusão de um certificado.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.SolicitarExclusao.Certificado.CertificadoSolicitacaoExclusao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idCertificado = 1; // ID do certificado no Intima.ai

    CertificadoSolicitacaoExclusao certificado = new CertificadoSolicitacaoExclusao(idCertificado);

    Object resultado = intimai.servicos.solicitarExclusao(certificado);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-certificados)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
