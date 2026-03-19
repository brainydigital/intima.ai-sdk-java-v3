package ai.intima.Servicos.Buscar.Notificacao;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class NotificacaoWebhookBusca implements ServicoBusca {
    private final String id;

    public NotificacaoWebhookBusca(String id) { this.id = id; }
    public NotificacaoWebhookBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.NOTIFICACAO + Endpoint.WEBHOOK + Endpoint.BUSCAR;
        return Endpoint.NOTIFICACAO + Endpoint.WEBHOOK + "/" + id + Endpoint.BUSCAR;
    }
}
