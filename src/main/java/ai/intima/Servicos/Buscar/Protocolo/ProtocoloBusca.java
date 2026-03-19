package ai.intima.Servicos.Buscar.Protocolo;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class ProtocoloBusca implements ServicoBusca {
    private final String id;

    public ProtocoloBusca(String id) { this.id = id; }
    public ProtocoloBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.PROTOCOLO + Endpoint.BUSCAR;
        return Endpoint.PROTOCOLO + "/" + id + Endpoint.BUSCAR;
    }
}
