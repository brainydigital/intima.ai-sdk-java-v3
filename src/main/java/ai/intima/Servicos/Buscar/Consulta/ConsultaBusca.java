package ai.intima.Servicos.Buscar.Consulta;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class ConsultaBusca implements ServicoBusca {
    private final String id;

    public ConsultaBusca(String id) { this.id = id; }
    public ConsultaBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.CONSULTA + Endpoint.BUSCAR;
        return Endpoint.CONSULTA + "/" + id + Endpoint.BUSCAR;
    }
}
