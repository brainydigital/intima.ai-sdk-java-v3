package ai.intima.Servicos.Buscar.Andamento;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class AndamentoBusca implements ServicoBusca {
    private final String id;

    public AndamentoBusca(String id) { this.id = id; }
    public AndamentoBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.ANDAMENTO + Endpoint.BUSCAR;
        return Endpoint.ANDAMENTO + "/" + id + Endpoint.BUSCAR;
    }
}
