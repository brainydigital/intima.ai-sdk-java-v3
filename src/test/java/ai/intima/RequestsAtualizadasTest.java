package ai.intima;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ai.intima.DadosTipo.Oab;
import ai.intima.Modelos.Documento;
import ai.intima.Modelos.ParteVinculada;
import ai.intima.Modelos.Peticao;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorAlterarParaRegularAtualizacao;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorRegularAtualizacao;
import ai.intima.Servicos.Cadastrar.Autenticador.AutenticadorCertificadoCadastro;
import ai.intima.Servicos.Cadastrar.Autenticador.AutenticadorRegularCadastro;
import ai.intima.Servicos.Solicitar.Consulta.ConsultaSolicitacao;
import ai.intima.Servicos.Solicitar.ConsultaPreAnalise.ConsultaPreAnaliseSolicitacao;
import ai.intima.Servicos.Solicitar.Protocolo.PJE.ProtocoloPjeSolicitacao;
import ai.intima.Servicos.Solicitar.Protocolo.TJRJ.ProtocoloTjrjSegundoPassoSolicitacao;
import ai.intima.Servicos.Solicitar.ProtocoloHabilitacao.PJE.ProtocoloHabilitacaoPjeSegundoPassoSolicitacao;
import ai.intima.Utilidades.Polo;
import ai.intima.Utilidades.TipoDeclaracao;
import ai.intima.Utilidades.TipoSolicitacao;

class RequestsAtualizadasTest {

    private static final Oab OAB = new Oab("12345", "A", "RJ");
    private static final String PROCESSO = "0000001-00.2026.8.19.0001";

    @TempDir
    Path pasta;

    private String arquivo(String conteudo) throws Exception {
        return Files.writeString(pasta.resolve(conteudo + ".pdf"), conteudo).toString();
    }

    @Test
    void consultaEPreConsultaNaoEnviamToken() throws Exception {
        Map<String, String> esperado = Map.of(
                "autenticacao_id", "5",
                "numero_processo", PROCESSO,
                "nome_parte", "",
                "nome_representante", "",
                "oab[numero]", "12345",
                "oab[letra]", "A",
                "oab[uf]", "RJ",
                "numero_documento_parte", "");

        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            intimai.servicos.solicitar(new ConsultaSolicitacao("5", PROCESSO, null, null, OAB, null));
            assertEquals("/api/v3/consulta-processual/solicitar", servidor.ultima().caminho());
            assertEquals(esperado, servidor.ultima().dados());

            intimai.servicos.solicitar(new ConsultaPreAnaliseSolicitacao("5", PROCESSO, null, null, OAB, null));
            assertEquals("/api/v3/pre-consulta-processual/solicitar", servidor.ultima().caminho());
            assertEquals(esperado, servidor.ultima().dados());
        }
    }

    @Test
    @SuppressWarnings("deprecation")
    void construtorAntigoDaConsultaIgnoraOTokenEUsaAPrimeiraOab() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            intimai.servicos.solicitar(new ConsultaSolicitacao("5", PROCESSO, "", "", List.of(OAB), "", "token-antigo"));
            Map<String, String> consulta = servidor.ultima().dados();
            intimai.servicos.solicitar(new ConsultaPreAnaliseSolicitacao("5", PROCESSO, "", "", List.of(), "", "token-antigo"));
            Map<String, String> preConsulta = servidor.ultima().dados();

            assertFalse(consulta.containsKey("token"));
            assertEquals("12345", consulta.get("oab[numero]"));
            assertFalse(preConsulta.containsKey("token"));
            assertFalse(preConsulta.containsKey("oab[numero]"));
        }
    }

    @Test
    void cadastroRegularContinuaIgualSemOsCamposNovos() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.cadastrar(new AutenticadorRegularCadastro("3", OAB, "usuario", "senha"));

            assertEquals(Map.of(
                    "tribunal_id", "3",
                    "usuario", "usuario",
                    "senha", "senha",
                    "oab_numero", "12345",
                    "oab_letra", "A",
                    "oab_uf", "RJ"), servidor.ultima().dados());
        }
    }

    @Test
    void cadastroRegularComVariosTribunaisCertificadoPerfilESecret() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.cadastrar(new AutenticadorRegularCadastro(List.of("3", "4"), OAB, "usuario", "senha",
                    "8", "ADV", "Escritório", "JBSWY3DPEHPK3PXP"));

            assertEquals("/api/v3/autenticador/regular/cadastrar", servidor.ultima().caminho());
            assertEquals(Map.ofEntries(
                    Map.entry("tribunal_id[0]", "3"),
                    Map.entry("tribunal_id[1]", "4"),
                    Map.entry("usuario", "usuario"),
                    Map.entry("senha", "senha"),
                    Map.entry("certificado_id", "8"),
                    Map.entry("oab_numero", "12345"),
                    Map.entry("oab_letra", "A"),
                    Map.entry("oab_uf", "RJ"),
                    Map.entry("usuario_sigla", "ADV"),
                    Map.entry("usuario_descricao", "Escritório"),
                    Map.entry("secret", "JBSWY3DPEHPK3PXP")), servidor.ultima().dados());
        }
    }

    @Test
    void cadastroPorCertificadoComVariosTribunaisESecret() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            intimai.servicos.cadastrar(new AutenticadorCertificadoCadastro("3,4", OAB, "8", null, null, "JBSWY3DPEHPK3PXP"));
            assertEquals("/api/v3/autenticador/certificado/cadastrar", servidor.ultima().caminho());
            assertEquals(Map.of(
                    "tribunal_id", "3,4",
                    "certificado_id", "8",
                    "oab_numero", "12345",
                    "oab_letra", "A",
                    "oab_uf", "RJ",
                    "secret", "JBSWY3DPEHPK3PXP"), servidor.ultima().dados());

            intimai.servicos.cadastrar(new AutenticadorCertificadoCadastro(List.of("3", "4"), OAB, "8", null, null, null));
            assertEquals("3", servidor.ultima().dados().get("tribunal_id[0]"));
            assertEquals("4", servidor.ultima().dados().get("tribunal_id[1]"));
            assertFalse(servidor.ultima().dados().containsKey("secret"));
        }
    }

    @Test
    void loginRegularAceitaCertificado() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            intimai.servicos.atualizar(new AutenticadorRegularAtualizacao("5", "usuario", "senha", "8"));
            assertEquals("PUT", servidor.ultima().metodo());
            assertEquals("/api/v3/autenticador/regular/5/atualizar", servidor.ultima().caminho());
            assertEquals(Map.of("usuario", "usuario", "senha", "senha", "certificado_id", "8"), servidor.ultima().dados());

            intimai.servicos.atualizar(new AutenticadorRegularAtualizacao("5", null, "nova"));
            assertEquals(Map.of("senha", "nova"), servidor.ultima().dados());

            intimai.servicos.atualizar(new AutenticadorAlterarParaRegularAtualizacao("5", "usuario", "senha", "8"));
            assertEquals("/api/v3/autenticador/regular/5/alterar-para-regular", servidor.ultima().caminho());
            assertEquals(Map.of("usuario", "usuario", "senha", "senha", "certificado_id", "8"), servidor.ultima().dados());

            intimai.servicos.atualizar(new AutenticadorAlterarParaRegularAtualizacao("5", "usuario", "senha"));
            assertEquals(Map.of("usuario", "usuario", "senha", "senha"), servidor.ultima().dados());
        }
    }

    @Test
    void protocoloPjeSemPeticaoNaoEnviaPeticao() throws Exception {
        Documento documento = new Documento(arquivo("doc"), 1, "21", "Documento");

        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.solicitar(new ProtocoloPjeSolicitacao("5", PROCESSO, "2", null, List.of(documento)));

            assertEquals("/api/v3/pje/protocolo-processual/solicitar", servidor.ultima().caminho());
            assertEquals(Map.of(
                    "numero_processo", PROCESSO,
                    "autenticacao_id", "5",
                    "tipo_documento_mensagem_geral", "2",
                    "documentos[0][arquivo]", "doc",
                    "documentos[0][tipo_documento]", "21",
                    "documentos[0][descricao_documento]", "Documento",
                    "documentos[0][ordem]", "1"), servidor.ultima().campos());
        }
    }

    @Test
    void protocoloPjeTrabalhistaEnviaPeticao() throws Exception {
        Peticao peticao = new Peticao(arquivo("peticao"), "7", "Petição");

        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.solicitar(new ProtocoloPjeSolicitacao("5", "0000001-00.2026.5.01.0001", "2", peticao, null));

            Map<String, String> campos = servidor.ultima().campos();
            assertEquals("peticao", campos.get("peticao[arquivo]"));
            assertEquals("7", campos.get("peticao[tipo_documento]"));
            assertEquals("Petição", campos.get("peticao[descricao_documento]"));
            assertTrue(servidor.ultima().texto().contains("name=\"peticao[arquivo]\"; filename=\"peticao.pdf\""));
        }
    }

    @Test
    void segundaEtapaTjrjEnviaCategoriaQuandoInformada() throws Exception {
        Peticao peticao = new Peticao(arquivo("peticao"), null, "Petição");
        List<ParteVinculada> partes = List.of(new ParteVinculada("Fulano", "Autor"));

        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            intimai.servicos.solicitar(new ProtocoloTjrjSegundoPassoSolicitacao("9", "100", partes, peticao, List.of(), "200"));
            Map<String, String> campos = servidor.ultima().campos();
            assertEquals("/api/v3/tjrj/protocolo-processual/9/segunda-etapa", servidor.ultima().caminho());
            assertEquals("100", campos.get("classe_id"));
            assertEquals("200", campos.get("categoria_id"));
            assertEquals("Fulano", campos.get("partes_vinculadas[0][nome]"));
            assertEquals("Autor", campos.get("partes_vinculadas[0][participacao]"));

            intimai.servicos.solicitar(new ProtocoloTjrjSegundoPassoSolicitacao("9", "100", partes, peticao));
            assertFalse(servidor.ultima().campos().containsKey("categoria_id"));
        }
    }

    @Test
    void segundaEtapaDaHabilitacaoEnviaComoACollection() throws Exception {
        Documento documento = new Documento(arquivo("procuracao"), 1, "21", "Procuração");

        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.solicitar(new ProtocoloHabilitacaoPjeSegundoPassoSolicitacao(
                    "9",
                    TipoSolicitacao.HABILITACAO_SIMPLES,
                    TipoDeclaracao.DECLARACAO_SOB_PENA_DE_LEI,
                    Polo.PASSIVO,
                    List.of(new ParteVinculada("node-1"), "node-2"),
                    "3",
                    "Descrição",
                    "Mensagem",
                    List.of(documento)));

            assertEquals("/api/v3/pje/protocolo-habilitacao/9/segunda-etapa", servidor.ultima().caminho());
            assertEquals(Map.ofEntries(
                    Map.entry("tipo_solicitacao", "0"),
                    Map.entry("tipo_declaracao", "0"),
                    Map.entry("polo", "1"),
                    Map.entry("tipo_documento_mensagem_geral", "3"),
                    Map.entry("descricao", "Descrição"),
                    Map.entry("mensagem_geral", "Mensagem"),
                    Map.entry("partes_vinculadas[0]", "node-1"),
                    Map.entry("partes_vinculadas[1]", "node-2"),
                    Map.entry("documentos[0][arquivo]", "procuracao"),
                    Map.entry("documentos[0][tipo_documento]", "21"),
                    Map.entry("documentos[0][descricao_documento]", "Procuração"),
                    Map.entry("documentos[0][ordem]", "1")), servidor.ultima().campos());
        }
    }

    @Test
    void segundaEtapaDaHabilitacaoSemDescricaoEMensagem() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.solicitar(new ProtocoloHabilitacaoPjeSegundoPassoSolicitacao(
                    "9",
                    TipoSolicitacao.HABILITACAO_SIMPLES,
                    TipoDeclaracao.TRT_NA_FORMA_DE_EXCECAO,
                    Polo.ATIVO,
                    List.of("node-1"),
                    "3"));

            Map<String, String> campos = servidor.ultima().campos();
            assertFalse(campos.containsKey("descricao"));
            assertFalse(campos.containsKey("mensagem_geral"));
            assertEquals("1", campos.get("tipo_declaracao"));
            assertEquals("node-1", campos.get("partes_vinculadas[0]"));
        }
    }
}
