package ai.intima;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ai.intima.Exceptions.APIRequestException;
import ai.intima.Exceptions.DoisFatoresException;
import ai.intima.Exceptions.LimiteDeRequisicoesException;
import ai.intima.Servicos.Buscar.Acao.AcaoBusca;

class ExcecaoTest {

    private static APIRequestException falhar(int status, String corpo) throws Exception {
        return falhar(status, corpo, Map.of());
    }

    private static APIRequestException falhar(int status, String corpo, Map<String, String> headers) throws Exception {
        try (ServidorFalso servidor = new ServidorFalso()) {
            Intimai intimai = servidor.responder(status, corpo, headers).intimai();
            return assertThrows(APIRequestException.class, () -> intimai.servicos.buscar(new AcaoBusca()));
        }
    }

    @Test
    void erroDeValidacaoExpoeStatusCorpoEErros() throws Exception {
        APIRequestException erro = falhar(422, "{\"autenticacao_id\":[\"O campo autenticacao id é obrigatório.\"]}");
        Map<String, Object> corpo = Map.of("autenticacao_id", List.of("O campo autenticacao id é obrigatório."));

        assertEquals(APIRequestException.class, erro.getClass());
        assertEquals(422, erro.getStatus());
        assertEquals(corpo, erro.getCorpo());
        assertEquals(corpo, erro.getErros());
        assertEquals(Map.of("code", 422, "error", corpo), erro.toJson());
    }

    @Test
    void erroDeNegocioComCodigoNaoViraErroDeValidacao() throws Exception {
        APIRequestException erro = falhar(422, "{\"error\":\"Complete seus dados fiscais.\",\"code\":613}");

        assertEquals(Map.of(), erro.getErros());
        assertEquals("Complete seus dados fiscais.", erro.getMensagem());
        assertEquals(613, ((Map<?, ?>) erro.getCorpo()).get("code"));
    }

    @Test
    void limitesDoPlanoESaldoTrazemAMensagemDaApi() throws Exception {
        assertEquals(457, falhar(457, "{\"error\":\"Limite por hora atingido.\"}").getStatus());
        assertEquals(458, falhar(458, "{\"error\":\"Certificado inválido.\"}").getStatus());

        APIRequestException saldo = falhar(459, "{\"error\":\"Você não possui saldo suficiente\"}");

        assertEquals(459, saldo.getStatus());
        assertEquals("Você não possui saldo suficiente", saldo.getMensagem());
    }

    @Test
    void erro401De2faViraDoisFatoresException() throws Exception {
        for (String mensagem : List.of("Código 2FA obrigatório", "Código 2FA inválido", "2FA ativado: conclua o cadastro no painel")) {
            APIRequestException erro = falhar(401, "{\"data\":{\"error\":true,\"message\":\"" + mensagem + "\"}}");

            assertInstanceOf(DoisFatoresException.class, erro);
            assertEquals(401, erro.getStatus());
            assertEquals(mensagem, erro.getMensagem());
        }
    }

    @Test
    void erro401DeTokenInvalidoNaoEDe2fa() throws Exception {
        APIRequestException erro = falhar(401, "{\"data\":{\"error\":true,\"message\":\"Unauthenticated\"}}");

        assertEquals(APIRequestException.class, erro.getClass());
        assertEquals("Unauthenticated", erro.getMensagem());
    }

    @Test
    void erro429De2faTrazRetryAfter() throws Exception {
        APIRequestException erro = falhar(429,
                "{\"data\":{\"error\":true,\"message\":\"Muitos códigos 2FA inválidos. Tente novamente em instantes.\"}}",
                Map.of("Retry-After", "42"));

        assertInstanceOf(LimiteDeRequisicoesException.class, erro);
        assertEquals(42, ((LimiteDeRequisicoesException) erro).getRetryAfter());
    }

    @Test
    void erro429DoLimiteGeralSemRetryAfterNumerico() throws Exception {
        APIRequestException erro = falhar(429, "{\"message\":\"Too Many Attempts.\"}", Map.of("Retry-After", "amanhã"));

        assertInstanceOf(LimiteDeRequisicoesException.class, erro);
        assertNull(((LimiteDeRequisicoesException) erro).getRetryAfter());
        assertEquals("Too Many Attempts.", erro.getMensagem());
    }

    @Test
    void corpoQueNaoEJsonFicaComoTexto() throws Exception {
        APIRequestException erro = falhar(500, "Erro interno");

        assertEquals(500, erro.getStatus());
        assertEquals("Erro interno", erro.getCorpo());
        assertEquals(Map.of(), erro.getErros());
        assertEquals(erro.getMessage(), erro.getMensagem());
        assertEquals("Erro HTTP 500: Erro interno", erro.getMessage());
    }

    @Test
    void falhaDeConexaoNaoTemStatusNemCorpo() {
        APIRequestException erro = assertThrows(APIRequestException.class,
                () -> new Intimai("token", Map.of("url_base", "http://127.0.0.1:1/api/v3")).servicos.buscar(new AcaoBusca()));

        assertNull(erro.getStatus());
        assertNull(erro.getCorpo());
        assertEquals(Map.of(), erro.getErros());
    }
}
