package ai.intima.Servicos.Buscar.ProtocoloHabilitacao;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class ProtocoloHabilitacaoBusca implements ServicoBusca {
    private final String id;

    public ProtocoloHabilitacaoBusca(String id) { this.id = id; }
    public ProtocoloHabilitacaoBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.PROTOCOLO_HABILITACAO + Endpoint.BUSCAR;
        return Endpoint.PROTOCOLO_HABILITACAO + "/" + id + Endpoint.BUSCAR;
    }
}
