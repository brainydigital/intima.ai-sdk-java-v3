package ai.intima.Servicos.Cadastrar.Notificacao;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Endpoint;

public class NotificacaoWebhookCadastro implements ServicoCadastro {
    private final String tipoAcao;
    private final String verboHttp;
    private final String url;

    public NotificacaoWebhookCadastro(String tipoAcao, String verboHttp, String url) {
        this.tipoAcao = tipoAcao;
        this.verboHttp = verboHttp;
        this.url = url;
    }

    @Override
    public String getEndpoint() { return Endpoint.NOTIFICACAO + Endpoint.WEBHOOK + Endpoint.CADASTRAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("tipo_acao", tipoAcao);
        formParams.put("verbo_http", verboHttp);
        formParams.put("url", url);
        Map<String, Object> request = new HashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
