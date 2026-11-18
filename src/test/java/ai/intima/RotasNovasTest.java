package ai.intima;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ai.intima.Exceptions.APIRequestException;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorDesabilitarAtualizacao;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorHabilitarAtualizacao;
import ai.intima.Servicos.Atualizar.Autenticador.AutenticadorPerfilAtualizacao;
import ai.intima.Servicos.Buscar.Acao.AcaoBusca;
import ai.intima.Servicos.Buscar.CapturaAgendada.CapturaAgendadaBusca;
import ai.intima.Servicos.Buscar.CodigoAjuda.CodigoAjudaBusca;
import ai.intima.Servicos.Buscar.Credito.CreditoConsumoMensalBusca;
import ai.intima.Servicos.Buscar.Protocolo.TipoDocumentoMensagemGeral.TipoDocumentoMensagemGeralProjudiBusca;
import ai.intima.Servicos.Cadastrar.Autenticador.AutenticadorDoisFatoresCadastro;
import ai.intima.Servicos.Solicitar.Acao.AcaoCancelarConsultaSolicitacao;
import ai.intima.Servicos.Solicitar.Acao.AcaoConfirmarConsultaSolicitacao;
import ai.intima.Servicos.Solicitar.Autenticador.AutenticadorCapturarIntimacoesSolicitacao;
import ai.intima.Utilidades.SituacaoCapturaAgendada;
import ai.intima.Utilidades.SituacaoNovaTentativa;
import ai.intima.Utilidades.StatusAcao;
import ai.intima.Utilidades.TipoCapturaAgendada;

class RotasNovasTest {

    private static void assertRequisicao(ServidorFalso servidor, String metodo, String caminho, Map<String, String> corpo) {
        ServidorFalso.Recebida recebida = servidor.ultima();

        assertEquals(metodo, recebida.metodo());
        assertEquals("/api/v3" + caminho, recebida.caminho());
        assertEquals("token-teste", recebida.query().get("api_token"));
        assertEquals(corpo, recebida.dados());
    }

    @Test
    void confirmarConsulta() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Map<String, Object> resultado = servidor.responder(200, "{\"message\":\"Consulta Processual confirmada!\",\"data\":{\"id\":9,\"status\":4}}")
                    .intimai().servicos.solicitar(new AcaoConfirmarConsultaSolicitacao("9"));

            assertRequisicao(servidor, "POST", "/acao/9/confirmar-consulta", Map.of());
            assertEquals(Map.of("message", "Consulta Processual confirmada!", "data", Map.of("id", 9, "status", StatusAcao.NA_FILA)), resultado);
        }
    }

    @Test
    void cancelarConsulta() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Map<String, Object> resultado = servidor.responder(200, "{\"data\":{\"id\":9,\"status\":14}}")
                    .intimai().servicos.solicitar(new AcaoCancelarConsultaSolicitacao("9"));

            assertRequisicao(servidor, "POST", "/acao/9/cancelar-consulta", Map.of());
            assertEquals(StatusAcao.CANCELADA, ((Map<?, ?>) resultado.get("data")).get("status"));
        }
    }

    @Test
    void confirmarConsultaDeOutroUsuarioDa404() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.responder(404, "{\"message\":\"Ação não encontrada.\"}").intimai();

            APIRequestException erro = assertThrows(APIRequestException.class,
                    () -> intimai.servicos.solicitar(new AcaoConfirmarConsultaSolicitacao("1")));

            assertEquals(404, erro.getStatus());
            assertEquals("Ação não encontrada.", erro.getMensagem());
        }
    }

    @Test
    void atualizarPerfilDoAutenticador() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.atualizar(new AutenticadorPerfilAtualizacao("5", "ADV", "Escritório"));

            assertRequisicao(servidor, "PUT", "/autenticador/5/atualizar-perfil",
                    Map.of("usuario_sigla", "ADV", "usuario_descricao", "Escritório"));
        }
    }

    @Test
    void atualizarPerfilEnviaSoOCampoInformado() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.atualizar(new AutenticadorPerfilAtualizacao("5", null, "Escritório"));

            assertRequisicao(servidor, "PUT", "/autenticador/5/atualizar-perfil", Map.of("usuario_descricao", "Escritório"));
        }
    }

    @Test
    void capturarIntimacoesAgora() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.solicitar(new AutenticadorCapturarIntimacoesSolicitacao("5"));

            assertRequisicao(servidor, "POST", "/autenticador/5/capturar-intimacoes", Map.of());
        }
    }

    @Test
    void capturarIntimacoesComCapturaDesligadaDa422() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.responder(422, "{\"message\":\"Ative a captura de intimações deste autenticador para capturar agora.\"}").intimai();

            APIRequestException erro = assertThrows(APIRequestException.class,
                    () -> intimai.servicos.solicitar(new AutenticadorCapturarIntimacoesSolicitacao("5")));

            assertEquals(422, erro.getStatus());
            assertEquals("Ative a captura de intimações deste autenticador para capturar agora.", erro.getMensagem());
            assertEquals(Map.of(), erro.getErros());
        }
    }

    @Test
    void habilitarEDesabilitarAutenticador() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            intimai.servicos.atualizar(new AutenticadorHabilitarAtualizacao("5"));
            assertRequisicao(servidor, "PUT", "/autenticador/5/habilitar", Map.of());

            intimai.servicos.atualizar(new AutenticadorDesabilitarAtualizacao("5"));
            assertRequisicao(servidor, "PUT", "/autenticador/5/desabilitar", Map.of());
        }
    }

    @Test
    void armazenar2faEmUmAutenticador() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.cadastrar(new AutenticadorDoisFatoresCadastro("5", "JBSWY3DPEHPK3PXP"));

            assertRequisicao(servidor, "POST", "/autenticador/5/2fa/armazenar", Map.of("secret", "JBSWY3DPEHPK3PXP"));
        }
    }

    @Test
    void armazenar2faEmVariosAutenticadores() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.cadastrar(new AutenticadorDoisFatoresCadastro(List.of("5", "6", "7"), "JBSWY3DPEHPK3PXP"));

            assertRequisicao(servidor, "POST", "/autenticador/5,6,7/2fa/armazenar", Map.of("secret", "JBSWY3DPEHPK3PXP"));
        }
    }

    @Test
    void consumoMensalComESemData() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.intimai();

            intimai.servicos.buscar(new CreditoConsumoMensalBusca(), 1, Map.of("date", "2026-10"));
            assertRequisicao(servidor, "GET", "/credito/consumo-mensal", Map.of());
            assertEquals("2026-10", servidor.ultima().query().get("date"));

            intimai.servicos.buscar(new CreditoConsumoMensalBusca());
            assertFalse(servidor.ultima().query().containsKey("date"));
        }
    }

    @Test
    void buscarCapturasAgendadasComFiltros() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.buscar(new CapturaAgendadaBusca(), 2, Map.of(
                    "data_inicial", "01/11/2026",
                    "data_final", "13/11/2026",
                    "autenticadores_ids", "5,6",
                    "tipos", TipoCapturaAgendada.INTIMACAO,
                    "situacoes", SituacaoCapturaAgendada.NAO_EXECUTADA + "," + SituacaoCapturaAgendada.ERRO));

            assertRequisicao(servidor, "GET", "/captura-agendada/buscar", Map.of());
            assertEquals(Map.of(
                    "api_token", "token-teste",
                    "page", "2",
                    "data_inicial", "01/11/2026",
                    "data_final", "13/11/2026",
                    "autenticadores_ids", "5,6",
                    "tipos", "intimacao",
                    "situacoes", "nao_executada,erro"), servidor.ultima().query());
        }
    }

    @Test
    void buscarUmaCapturaAgendada() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.buscar(new CapturaAgendadaBusca("31"));

            assertRequisicao(servidor, "GET", "/captura-agendada/31/buscar", Map.of());
        }
    }

    @Test
    void constantesDeStatusESituacaoBatemComAApi() {
        assertEquals(13, StatusAcao.AGUARDANDO_CONFIRMACAO);
        assertEquals(14, StatusAcao.CANCELADA);
        assertEquals("escuta", TipoCapturaAgendada.ESCUTA);
        assertEquals(List.of("em_andamento", "finalizada", "nao_executada", "erro"), List.of(
                SituacaoCapturaAgendada.EM_ANDAMENTO, SituacaoCapturaAgendada.FINALIZADA,
                SituacaoCapturaAgendada.NAO_EXECUTADA, SituacaoCapturaAgendada.ERRO));
        assertEquals(List.of("agendada", "aguardando_condicao", "encerrada", "sem_nova_tentativa"), List.of(
                SituacaoNovaTentativa.AGENDADA, SituacaoNovaTentativa.AGUARDANDO_CONDICAO,
                SituacaoNovaTentativa.ENCERRADA, SituacaoNovaTentativa.SEM_NOVA_TENTATIVA));
    }

    @Test
    void buscarTiposDocumentoMensagemGeralDoProjudi() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Map<String, Object> resultado = servidor.responder(200, "{\"message\":\"Tipos documentos mensagem geral encontrados para o tribunal do autenticador selecionado!\",\"data\":[{\"id\":1,\"nome\":\"Petição\"}]}")
                    .intimai().servicos.buscar(new TipoDocumentoMensagemGeralProjudiBusca("5"));

            assertRequisicao(servidor, "GET", "/projudi/protocolo-processual/5/tipos-documento-mensagem-geral", Map.of());
            assertEquals(List.of(Map.of("id", 1, "nome", "Petição")), resultado.get("data"));
        }
    }

    @Test
    void tiposDocumentoMensagemGeralComAutenticadorForaDoProjudiDa422() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.responder(422, "{\"message\":\"Este autenticador não pertence ao sistema Projudi\"}").intimai();

            APIRequestException erro = assertThrows(APIRequestException.class,
                    () -> intimai.servicos.buscar(new TipoDocumentoMensagemGeralProjudiBusca("5")));

            assertEquals(422, erro.getStatus());
            assertEquals("Este autenticador não pertence ao sistema Projudi", erro.getMensagem());
        }
    }

    @Test
    void buscarCatalogoDeCodigosDeAjuda() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Map<String, Object> resultado = servidor.responder(200, "{\"message\":\"Códigos encontrados!\",\"data\":{\"faixas\":[{\"value\":\"isenta\",\"total\":47}],\"nova_tentativa_aviso\":\"A nova tentativa automática só acontece nos códigos marcados.\",\"grupos\":[{\"nome\":\"Erros Gerais\",\"codigos\":[{\"codigo\":606,\"faixa\":\"isenta\",\"nova_tentativa\":\"Nova tentativa automática: até 3×, a cada 30 min.\"}]}]}}")
                    .intimai().servicos.buscar(new CodigoAjudaBusca());

            assertRequisicao(servidor, "GET", "/codigo-ajuda/buscar", Map.of());
            assertFalse(servidor.ultima().query().containsKey("codigo"));

            Map<?, ?> data = (Map<?, ?>) resultado.get("data");
            Map<?, ?> grupo = (Map<?, ?>) ((List<?>) data.get("grupos")).get(0);
            Map<?, ?> codigo = (Map<?, ?>) ((List<?>) grupo.get("codigos")).get(0);
            assertEquals(606, codigo.get("codigo"));
            assertEquals("Nova tentativa automática: até 3×, a cada 30 min.", codigo.get("nova_tentativa"));
        }
    }

    @Test
    void buscarCodigoDeAjudaPeloCodigo() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            servidor.intimai().servicos.buscar(new CodigoAjudaBusca(), 1, Map.of("codigo", "606"));

            assertRequisicao(servidor, "GET", "/codigo-ajuda/buscar", Map.of());
            assertEquals("606", servidor.ultima().query().get("codigo"));
        }
    }

    @Test
    void codigoDeAjudaForaDoCatalogoDa404() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.responder(404, "{\"message\":\"Nenhum código encontrado no catálogo.\"}").intimai();

            APIRequestException erro = assertThrows(APIRequestException.class,
                    () -> intimai.servicos.buscar(new CodigoAjudaBusca(), 1, Map.of("codigo", "99999")));

            assertEquals(404, erro.getStatus());
            assertEquals("Nenhum código encontrado no catálogo.", erro.getMensagem());
        }
    }

    @Test
    void acaoComErroTrazANovaTentativaAutomatica() throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Map<String, Object> resultado = servidor.responder(200, "{\"data\":{\"id\":9,\"status\":3,\"nova_tentativa\":{\"automatica\":true,\"situacao\":\"agendada\",\"tentativa\":1,\"max_tentativas\":3,\"prevista_para\":\"2026-11-18 10:30:00\",\"mensagem\":\"Nova tentativa automática 1 de 3 a partir de 10:30. Não é preciso criar outra ação.\"}}}")
                    .intimai().servicos.buscar(new AcaoBusca("9"));

            assertRequisicao(servidor, "GET", "/acao/9/buscar", Map.of());

            Map<?, ?> data = (Map<?, ?>) resultado.get("data");
            Map<?, ?> novaTentativa = (Map<?, ?>) data.get("nova_tentativa");
            assertEquals(StatusAcao.ERRO, data.get("status"));
            assertEquals(true, novaTentativa.get("automatica"));
            assertEquals(SituacaoNovaTentativa.AGENDADA, novaTentativa.get("situacao"));
            assertEquals(3, novaTentativa.get("max_tentativas"));
            assertEquals("2026-11-18 10:30:00", novaTentativa.get("prevista_para"));
        }
    }
}
