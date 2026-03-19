package ai.intima.Servicos.Buscar.Escuta;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class EscutaBusca implements ServicoBusca {
    private final String id;

    public EscutaBusca(String id) { this.id = id; }
    public EscutaBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.ESCUTA + Endpoint.BUSCAR;
        return Endpoint.ESCUTA + "/" + id + Endpoint.BUSCAR;
    }
}
