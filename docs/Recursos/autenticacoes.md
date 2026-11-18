# Recurso: **Autenticações**

> Os Autenticadores do `Intima.ai` são utilizados para ter acesso aos tribunais.
> Você pode criar diversas autenticações para diversos tribunais sendo uma autenticação para cada tribunal.
>
> No cadastro, `idTribunal` aceita um id (`"1"`), um texto com vários (`"1,2"`) ou uma lista (`List.of("1", "2")`): a API cria uma autenticação para cada tribunal informado.
>
> Alguns tribunais exigem autenticação de dois fatores (2FA) no login. Para eles, informe a `secret` (Base32, mínimo de 16 caracteres) no cadastro ou depois, com [Armazenar 2FA](#armazenar-2fa-do-tribunal).
> Alguns tribunais também exigem um certificado no login por usuário/senha: nesses casos, informe `idCertificado`.

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

    String idAutenticador = "1"; // ID do autenticador no Intima.ai

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
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorOabAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idAutenticador = "1"; // ID do autenticador no Intima.ai

    // Número da OAB, letra (opcional, pode ser null) e UF (ex: RJ, PE, PB...)
    Oab oab = new Oab("numero_valor", null, "uf_valor");

    AutenticadorOabAtualizacao autenticador = new AutenticadorOabAtualizacao(idAutenticador, oab);

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
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorRegularAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idAutenticador = "1"; // ID do autenticador no Intima.ai
    String usuario = "usuario-novo-valor"; // Opcional se estiver alterando apenas a senha
    String senha = "senha-nova-valor";     // Opcional se estiver alterando apenas o usuário
    String idCertificado = null; // Opcional; obrigatório nos tribunais que exigem certificado no login regular

    AutenticadorRegularAtualizacao autenticador = new AutenticadorRegularAtualizacao(idAutenticador, usuario, senha, idCertificado);

    Object resultado = intimai.servicos.atualizar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Atualizar Perfil

Exemplo para atualizar a sigla e/ou a descrição do perfil do autenticador. Informe pelo menos um dos dois (o outro pode ser `null`).

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorPerfilAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idAutenticador = "1"; // ID do autenticador no Intima.ai

    AutenticadorPerfilAtualizacao autenticador = new AutenticadorPerfilAtualizacao(idAutenticador, "ADV", "Advogado");

    Object resultado = intimai.servicos.atualizar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
}
```

### **Situação**

Exemplo para habilitar ou desabilitar um autenticador. Desabilitado, ele não solicita nenhuma ação até ser habilitado de novo. A API responde 422 se o autenticador já estiver na situação pedida.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorHabilitarAtualizacao;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorDesabilitarAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idAutenticador = "1"; // ID do autenticador no Intima.ai

    Object resultado = intimai.servicos.atualizar(new AutenticadorHabilitarAtualizacao(idAutenticador));
    // ou: intimai.servicos.atualizar(new AutenticadorDesabilitarAtualizacao(idAutenticador));

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.getStatus() + " " + e.getMensagem());
}
```

### **Armazenar 2FA do tribunal**

Exemplo para armazenar a `secret` do 2FA do tribunal em um ou mais autenticadores. A `secret` é a chave Base32 (letras A–Z e dígitos 2–7, mínimo de 16 caracteres) mostrada pelo tribunal ao configurar o app autenticador.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Cadastrar.Autenticador.AutenticadorDoisFatoresCadastro;
import ai.intima.Exceptions.APIRequestException;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    List<String> idsAutenticadores = List.of("1", "2"); // Um ID ("1") ou uma lista de IDs de autenticadores

    AutenticadorDoisFatoresCadastro doisFatores = new AutenticadorDoisFatoresCadastro(idsAutenticadores, "JBSWY3DPEHPK3PXP");

    Object resultado = intimai.servicos.cadastrar(doisFatores);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.getErros());
}
```

### **Tipo**

Exemplos dos serviços disponíveis para criar autenticadores ou alterar o autenticador de Certificado para Regular e vice-versa.

#### Alterar para Usuário/Senha

Exemplo para realizar a alteração do tipo do autenticador para usuário/senha (obrigatório o autenticador ser previamente do tipo Certificado A1).

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorAlterarParaRegularAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idAutenticador = "1"; // ID do autenticador no Intima.ai
    String usuario = "usuario-valor"; // Usuário que será utilizado para acesso ao tribunal
    String senha = "senha-valor";     // Senha que será utilizada para acesso ao tribunal
    String idCertificado = null; // Opcional; obrigatório nos tribunais que exigem certificado no login regular

    AutenticadorAlterarParaRegularAtualizacao autenticador = new AutenticadorAlterarParaRegularAtualizacao(idAutenticador, usuario, senha, idCertificado);

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
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorAlterarParaCertificadoAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idAutenticador = "1"; // ID do autenticador no Intima.ai
    String idCertificado = "1";  // ID do certificado no Intima.ai

    AutenticadorAlterarParaCertificadoAtualizacao autenticador = new AutenticadorAlterarParaCertificadoAtualizacao(idAutenticador, idCertificado);

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

    String idTribunal = "1"; // ID do tribunal no Intima.ai, ou vários: "1,2"

    // Número da OAB, letra (opcional, pode ser null) e UF (ex: RJ, PE, PB...)
    Oab oab = new Oab("numero_valor", null, "uf_valor");

    String idCertificado = "1";

    AutenticadorCertificadoCadastro autenticador = new AutenticadorCertificadoCadastro(
        idTribunal,
        oab,
        idCertificado,
        null, // Sigla do perfil. Opcional
        null, // Descrição do perfil. Opcional
        null  // secret do 2FA. Obrigatória se algum tribunal exigir 2FA
    );

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

    String idTribunal = "1"; // ID do tribunal no Intima.ai, ou vários: "1,2"

    // Número da OAB, letra (opcional, pode ser null) e UF (ex: RJ, PE, PB...)
    Oab oab = new Oab("numero_valor", null, "uf_valor");

    String usuario = "usuario-valor"; // Usuário que será utilizado para acesso ao tribunal
    String senha = "senha-valor";     // Senha que será utilizada para acesso ao tribunal

    AutenticadorRegularCadastro autenticador = new AutenticadorRegularCadastro(
        idTribunal,
        oab,
        usuario,
        senha,
        null, // ID do certificado. Obrigatório nos tribunais que exigem certificado no login regular
        null, // Sigla do perfil. Opcional
        null, // Descrição do perfil. Opcional
        null  // secret do 2FA. Obrigatória se algum tribunal exigir 2FA
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
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorAtivarCapturaIntimacaoAtualizacao;
import ai.intima.Exceptions.APIRequestException;
import java.util.Arrays;
import java.util.List;

try {
    Intimai intimai = new Intimai("api_token");

    // ID do autenticador no Intima.ai
    String idAutenticador = "1";

    // Abas disponíveis no tribunal para o qual você está ativando a captura
    List<String> abas = Arrays.asList("aba_1", "aba_2");

    // Dias no qual a captura será realizada
    List<Integer> diasDeCaptura = Arrays.asList(Dia.SEGUNDA_FEIRA, Dia.SEXTA_FEIRA);

    // Horários no qual a captura será realizada (no máximo 3, de 01:00 a 23:00)
    List<String> horariosDeCaptura = Arrays.asList(Horario.DEZ_HORAS, Horario.DEZOITO_HORAS);

    // Por padrão, este valor é 0 (false), se quiser ativar, basta definir como 1 (true)
    int capturarProcuradorias = 1;

    AutenticadorAtivarCapturaIntimacaoAtualizacao autenticador = new AutenticadorAtivarCapturaIntimacaoAtualizacao(
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
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorDesativarCapturaIntimacaoAtualizacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    // ID do autenticador no Intima.ai
    String idAutenticador = "1";

    AutenticadorDesativarCapturaIntimacaoAtualizacao autenticador = new AutenticadorDesativarCapturaIntimacaoAtualizacao(idAutenticador);

    Object resultado = intimai.servicos.atualizar(autenticador);

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.toJson());
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

#### Capturar Agora

Exemplo para pedir uma captura de intimações fora dos horários agendados. A captura do autenticador precisa estar ativa; caso contrário, a API responde 422.

```java
import ai.intima.Intimai;
import ai.intima.Servicos.Solicitar.Autenticador.AutenticadorCapturarIntimacoesSolicitacao;
import ai.intima.Exceptions.APIRequestException;

try {
    Intimai intimai = new Intimai("api_token");

    String idAutenticador = "1"; // ID do autenticador no Intima.ai

    Object resultado = intimai.servicos.solicitar(new AutenticadorCapturarIntimacoesSolicitacao(idAutenticador));

    System.out.println(resultado);
} catch (APIRequestException e) {
    System.out.println(e.getStatus() + " " + e.getMensagem());
}
```

[Voltar ao topo](#recurso-autenticações)
[Voltar a lista da API](../../README.md#recursos)
[Voltar para o README](../../README.md#intimaai--sdk-v3-java)
