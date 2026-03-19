package ai.intima.Servicos.Atualizar.Notificacao;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class NotificacaoEmailAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final String email;

    public NotificacaoEmailAtualizacao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public String getEndpoint() { return Endpoint.NOTIFICACAO + Endpoint.EMAIL + "/" + id + Endpoint.ATUALIZAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> request = new HashMap<>();
        request.put("email", email);
        return request;
    }
}
