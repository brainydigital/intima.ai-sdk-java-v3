package ai.intima.Servicos.Buscar.Notificacao;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class NotificacaoEmailBusca implements ServicoBusca {
    private final String id;

    public NotificacaoEmailBusca(String id) { this.id = id; }
    public NotificacaoEmailBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.NOTIFICACAO + Endpoint.EMAIL + Endpoint.BUSCAR;
        return Endpoint.NOTIFICACAO + Endpoint.EMAIL + "/" + id + Endpoint.BUSCAR;
    }
}
