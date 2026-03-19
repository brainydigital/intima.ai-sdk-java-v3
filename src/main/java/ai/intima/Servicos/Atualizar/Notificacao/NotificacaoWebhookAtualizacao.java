package ai.intima.Servicos.Atualizar.Notificacao;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class NotificacaoWebhookAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final String tipoAcao;
    private final String verboHttp;
    private final String url;

    public NotificacaoWebhookAtualizacao(String id, String tipoAcao, String verboHttp, String url) {
        this.id = id;
        this.tipoAcao = tipoAcao;
        this.verboHttp = verboHttp;
        this.url = url;
    }

    @Override
    public String getEndpoint() { return Endpoint.NOTIFICACAO + Endpoint.WEBHOOK + "/" + id + Endpoint.ATUALIZAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> request = new HashMap<>();
        request.put("tipo_acao", tipoAcao);
        request.put("verbo_http", verboHttp);
        request.put("url", url);
        return request;
    }
}
