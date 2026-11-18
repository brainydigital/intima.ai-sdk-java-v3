package ai.intima;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ai.intima.API.API;
import ai.intima.DadosTipo.Oab;
import ai.intima.Exceptions.APIRequestException;
import ai.intima.Modelos.Certificado;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorAtivarCapturaIntimacaoAtualizacao;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorOabAtualizacao;
import ai.intima.Servicos.Atualizar.Escuta.EscutaAtualizacao;
import ai.intima.Servicos.Buscar.Acao.AcaoBusca;
import ai.intima.Servicos.Cadastrar.Notificacao.NotificacaoEmailCadastro;
import ai.intima.Servicos.SolicitarCadastro.Certificado.CertificadoSolicitacaoCadastro;
import ai.intima.Utilidades.Dia;
import ai.intima.Utilidades.Horario;

class APITest {

    @TempDir
    Path pasta;

    private static List<List<String>> pares(ServidorFalso.Recebida recebida) {
        return recebida.formulario().stream().map(List::of).toList();
    }

    @Test
    void enviaTokenNaQueryEHeadersDaCollection() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.cadastrar(new NotificacaoEmailCadastro("a@b.com"));

            ServidorFalso.Recebida recebida = servidor.ultima();
            assertEquals("POST", recebida.metodo());
            assertEquals("/api/v3/notificacao/email/cadastrar", recebida.caminho());
            assertEquals(Map.of("api_token", "token-teste", "page", "1"), recebida.query());
            assertEquals("application/json", recebida.header("Accept"));
            assertEquals("application/x-www-form-urlencoded", recebida.header("Content-Type"));
            assertEquals(API.USER_AGENT, recebida.header("User-Agent"));
            assertNull(recebida.header("X-Codigo-2FA"));
            assertNull(recebida.header("Authorization"));
            assertEquals(List.of(List.of("email", "a@b.com")), pares(recebida));
        }
    }

    @Test
    void corpoDeFormularioUsaColchetesParaListasEObjetos() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            intimai.servicos.atualizar(new AutenticadorAtivarCapturaIntimacaoAtualizacao("5", List.of("aba"),
                    List.of(Dia.SEGUNDA_FEIRA, Dia.SEXTA_FEIRA), List.of(Horario.DEZ_HORAS), true));
            intimai.servicos.atualizar(new EscutaAtualizacao("7", new int[] {Dia.SEGUNDA_FEIRA}, List.of(Horario.DEZ_HORAS, Horario.DEZOITO_HORAS)));
            intimai.servicos.atualizar(new AutenticadorOabAtualizacao("5", new Oab("123", "RJ")));

            assertEquals("/api/v3/autenticador/5/ativar-captura-intimacao", servidor.recebida(0).caminho());
            assertEquals(List.of(
                    List.of("abas[0]", "aba"),
                    List.of("capturar_procuradorias", "1"),
                    List.of("dias_de_captura[0]", "1"),
                    List.of("dias_de_captura[1]", "5"),
                    List.of("horarios_de_captura[0]", "10:00")
            ), pares(servidor.recebida(0)).stream().sorted((a, b) -> a.get(0).compareTo(b.get(0))).toList());
            assertEquals(List.of(
                    List.of("dias_de_captura[0]", "1"),
                    List.of("horarios_de_captura[0]", "10:00"),
                    List.of("horarios_de_captura[1]", "18:00")
            ), pares(servidor.recebida(1)).stream().sorted((a, b) -> a.get(0).compareTo(b.get(0))).toList());
            assertEquals(Map.of("oab_numero", "123", "oab_letra", "", "oab_uf", "RJ"),
                    servidor.recebida(2).formulario().stream().collect(java.util.stream.Collectors.toMap(p -> p[0], p -> p[1])));
        }
    }

    @Test
    void codigo2faVaiNoHeaderENuncaNaQuery() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai(Map.of("codigo_2fa", "482913")).servicos.buscar(new AcaoBusca());

            assertEquals("482913", servidor.ultima().header("X-Codigo-2FA"));
            assertFalse(servidor.ultima().query().containsKey("codigo_2fa"));
        }
    }

    @Test
    void codigo2faPorFuncaoEGeradoACadaRequest() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Deque<String> codigos = new ArrayDeque<>(List.of("111111", "222222"));
            Intimai intimai = servidor.intimai();
            intimai.definirCodigo2fa(codigos::poll);

            intimai.servicos.buscar(new AcaoBusca());
            intimai.servicos.buscar(new AcaoBusca());

            assertEquals("111111", servidor.recebida(0).header("X-Codigo-2FA"));
            assertEquals("222222", servidor.recebida(1).header("X-Codigo-2FA"));
        }
    }

    @Test
    void definirCodigo2faTrocaOuRemoveOCodigo() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            intimai.definirCodigo2fa("654321");
            intimai.servicos.buscar(new AcaoBusca());
            intimai.definirCodigo2fa((String) null);
            intimai.servicos.buscar(new AcaoBusca());
            intimai.definirCodigo2fa("");
            intimai.servicos.buscar(new AcaoBusca());

            assertEquals("654321", servidor.recebida(0).header("X-Codigo-2FA"));
            assertNull(servidor.recebida(1).header("X-Codigo-2FA"));
            assertNull(servidor.recebida(2).header("X-Codigo-2FA"));
        }
    }

    @Test
    void headersEParamsDasOpcoesSomamAosPadrao() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai(Map.of("headers", Map.of("X-Rastreio", "abc"), "params", Map.of("extra", "1")))
                    .servicos.buscar(new AcaoBusca());

            assertEquals("abc", servidor.ultima().header("X-Rastreio"));
            assertEquals("application/json", servidor.ultima().header("Accept"));
            assertEquals(Map.of("api_token", "token-teste", "page", "1", "extra", "1"), servidor.ultima().query());
        }
    }

    @Test
    void filtrosEPaginaVaoNaQuery() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.buscar(new AcaoBusca(), 3, Map.of("status", "13,14", "per_page", "50"));

            assertEquals(Map.of("api_token", "token-teste", "page", "3", "status", "13,14", "per_page", "50"), servidor.ultima().query());
        }
    }

    @Test
    void multipartEnviaOArquivoComNome() throws Exception {
        Path certificado = Files.writeString(pasta.resolve("arquivo.pfx"), "conteudo-pfx");

        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.solicitarCadastro(new CertificadoSolicitacaoCadastro(new Certificado(certificado.toString(), "senha")));

            ServidorFalso.Recebida recebida = servidor.ultima();
            assertEquals("/api/v3/certificado/solicitar-cadastro", recebida.caminho());
            assertTrue(recebida.header("Content-Type").startsWith("multipart/form-data; boundary="));
            assertEquals("application/json", recebida.header("Accept"));
            assertTrue(recebida.texto().contains("name=\"arquivo\"; filename=\"arquivo.pfx\""));
            assertEquals(Map.of("arquivo", "conteudo-pfx", "senha", "senha", "senha_confirmation", "senha"), recebida.campos());
        }
    }

    @Test
    void arquivoInexistenteFalhaAntesDeChamarAApi() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            RuntimeException erro = assertThrows(RuntimeException.class,
                    () -> intimai.servicos.solicitarCadastro(new CertificadoSolicitacaoCadastro(new Certificado("/nao/existe.pfx", "senha"))));

            assertEquals("O caminho do arquivo informado é inválido!", erro.getMessage());
            assertTrue(servidor.recebidas.isEmpty());
        }
    }

    @Test
    void respostaSemCorpoDevolveMapaVazio() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            assertEquals(Map.of(), servidor.responder(204, "").intimai().servicos.buscar(new AcaoBusca()));
        }
    }

    @Test
    void urlPadraoContinuaSendoADeProducao() {
        assertEquals("https://app.intima.ai/api/v3", API.URL_BASE);
        assertDoesNotThrow(() -> new Intimai("token"));
        assertThrows(APIRequestException.class, () -> new Intimai("token", Map.of("url_base", "http://127.0.0.1:1/api/v3")).servicos.buscar(new AcaoBusca()));
    }
}
