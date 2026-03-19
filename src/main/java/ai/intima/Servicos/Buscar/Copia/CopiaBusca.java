package ai.intima.Servicos.Buscar.Copia;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class CopiaBusca implements ServicoBusca {
    private final String id;

    public CopiaBusca(String id) { this.id = id; }
    public CopiaBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.COPIA + Endpoint.BUSCAR;
        return Endpoint.COPIA + "/" + id + Endpoint.BUSCAR;
    }
}
