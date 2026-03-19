# Recurso: **Autenticações**

> Os Autenticadores do `Intima.ai` são utilizados para ter acesso aos tribunais.
> Você pode criar diversas autenticações para diversos tribunais sendo uma autenticação para cada tribunal.

Todas as URIs são relativas a *https://app.intima.ai/api/v3*

## Exemplos

Para solicitar serviços referentes ao autenticador, você deve instanciar o tipo de serviço antes e inserir nas funções padrões dos serviços. Veja os serviços disponíveis para o autenticador nos exemplos abaixo.

### **Busca**

> Obs: Lembre-se que é possível paginar ou aplicar filtros as buscas dos serviços.

#### Geral

Exemplo para realizar uma busca geral de todas as autenticações do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Autenticador.AutenticadorBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // Se não for inserido nenhum id no serviço busca, ele irá retornar todos os registros daquele serviço
    AutenticadorBusca autenticadores = new AutenticadorBusca();

    Object resultado = intimai.servicos.buscar(autenticadores);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Específica

Exemplo para realizar uma busca específica de uma autenticação do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Buscar.Autenticador.AutenticadorBusca;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai

    AutenticadorBusca autenticador = new AutenticadorBusca(idAutenticador);

    Object resultado = intimai.servicos.buscar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Dados**

Exemplos dos serviços disponíveis para atualização dos dados.

#### Atualizar Dados OAB

Exemplo para realizar a atualização dos dados referentes a OAB do autenticador específico do usuário.

```java
import ai.intima.Intimai;
import ai.intima.DadosTipo.Oab;
import ai.intima.Servicos.Atualizar.Autenticador.OabAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai

    // Número da OAB, letra (opcional, pode ser null) e UF (ex: RJ, PE, PB...)
    Oab oab = new Oab("numero_valor", null, "uf_valor");

    OabAtualizacao autenticador = new OabAtualizacao(idAutenticador, oab);

    Object resultado = intimai.servicos.atualizar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Atualizar Dados Usuário/Senha

Exemplo para realizar a atualização dos dados referentes ao usuário/senha (obrigatório ser do tipo regular) do autenticador específico do usuário.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Atualizar.Autenticador.RegularAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String usuario = "usuario-novo-valor"; // Opcional se estiver alterando apenas a senha
    String senha = "senha-nova-valor";     // Opcional se estiver alterando apenas o usuário

    RegularAtualizacao autenticador = new RegularAtualizacao(idAutenticador, usuario, senha);

    Object resultado = intimai.servicos.atualizar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Tipo**

Exemplos dos serviços disponíveis para criar autenticadores ou alterar o autenticador de Certificado para Regular e vice-versa.

#### Alterar para Usuário/Senha

Exemplo para realizar a alteração do tipo do autenticador para usuário/senha (obrigatório o autenticador ser previamente do tipo Certificado A1).

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Atualizar.Autenticador.AlterarParaRegularAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    String usuario = "usuario-valor"; // Usuário que será utilizado para acesso ao tribunal
    String senha = "senha-valor";     // Senha que será utilizada para acesso ao tribunal

    AlterarParaRegularAtualizacao autenticador = new AlterarParaRegularAtualizacao(idAutenticador, usuario, senha);

    Object resultado = intimai.servicos.atualizar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Alterar para Certificado

Exemplo para realizar a alteração do tipo do autenticador para certificado A1 (obrigatório o autenticador ser previamente do tipo usuário/senha).

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Atualizar.Autenticador.AlterarParaCertificadoAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idAutenticador = 1; // ID do autenticador no Intima.ai
    int idCertificado = 1;  // ID do certificado no Intima.ai

    AlterarParaCertificadoAtualizacao autenticador = new AlterarParaCertificadoAtualizacao(idAutenticador, idCertificado);

    Object resultado = intimai.servicos.atualizar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Criar com Certificado

Exemplo para realizar o cadastro de um autenticador do tipo certificado A1.

```java
import ai.intima.Intimai;
import ai.intima.DadosTipo.Oab;
import ai.intima.Servicos.Cadastrar.Autenticador.AutenticadorCertificadoCadastro;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idTribunal = 1; // ID do tribunal no Intima.ai

    // Número da OAB, letra (opcional, pode ser null) e UF (ex: RJ, PE, PB...)
    Oab oab = new Oab("numero_valor", null, "uf_valor");

    int idCertificado = 1;

    AutenticadorCertificadoCadastro autenticador = new AutenticadorCertificadoCadastro(idTribunal, oab, idCertificado);

    Object resultado = intimai.servicos.cadastrar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Criar com Usuário/Senha

Exemplo para realizar o cadastro de um autenticador do tipo regular (usuário/senha).

```java
import ai.intima.Intimai;
import ai.intima.DadosTipo.Oab;
import ai.intima.Servicos.Cadastrar.Autenticador.AutenticadorRegularCadastro;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    int idTribunal = 1; // ID do tribunal no Intima.ai

    // Número da OAB, letra (opcional, pode ser null) e UF (ex: RJ, PE, PB...)
    Oab oab = new Oab("numero_valor", null, "uf_valor");

    String usuario = "usuario-valor"; // Usuário que será utilizado para acesso ao tribunal
    String senha = "senha-valor";     // Senha que será utilizada para acesso ao tribunal

    AutenticadorRegularCadastro autenticador = new AutenticadorRegularCadastro(
        idTribunal,
        oab,
        usuario,
        senha
    );

    Object resultado = intimai.servicos.cadastrar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

### **Intimação**

Exemplos dos serviços disponíveis para ativar/desativar captura de intimações do autenticador.

#### Ativar Captura

Exemplo para ativar a captura de intimações de um autenticador.

```java
import ai.intima.Intimai;
import ai.intima.Utilidades.Dia;
import ai.intima.Utilidades.Horario;
import ai.intima.Servicos.Atualizar.Autenticador.AtivarCapturaIntimacaoAtualizacao;
import ai.intima.Exceptions.APIRequestException;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    // ID do autenticador no Intima.ai
    int idAutenticador = 1;

    // Abas disponíveis no tribunal para o qual você está ativando a captura
    List<String> abas = Arrays.asList("aba_1", "aba_2");

    // Dias no qual a captura será realizada
    List<Integer> diasDeCaptura = Arrays.asList(Dia.SEGUNDA_FEIRA, Dia.SEXTA_FEIRA);

    // Horários no qual a captura será realizada
    List<String> horariosDeCaptura = Arrays.asList(Horario.DEZ_HORAS, Horario.DEZOITO_HORAS);

    // Por padrão, este valor é 0 (false), se quiser ativar, basta definir como 1 (true)
    int capturarProcuradorias = 1;

    AtivarCapturaIntimacaoAtualizacao autenticador = new AtivarCapturaIntimacaoAtualizacao(
        idAutenticador,
        abas,
        diasDeCaptura,
        horariosDeCaptura,
        capturarProcuradorias
    );

    Object resultado = intimai.servicos.atualizar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Desativar Captura

Exemplo para desativar a captura de intimações de um autenticador.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Atualizar.Autenticador.DesativarCapturaIntimacaoAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // ID do autenticador no Intima.ai
    int idAutenticador = 1;

    DesativarCapturaIntimacaoAtualizacao autenticador = new DesativarCapturaIntimacaoAtualizacao(idAutenticador);

    Object resultado = intimai.servicos.atualizar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

[Voltar ao topo](#recurso-autenticações)
[Voltar a lista da API](../../README.md#documentação-para-os-endpoints-da-api)
[Voltar para o README](../../README.md#intimaai---sdk-v3-java)
