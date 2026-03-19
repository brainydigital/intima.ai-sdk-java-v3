package ai.intima.Servicos.Buscar.ConsultaPreAnalise;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class ConsultaPreAnaliseBusca implements ServicoBusca {
    private final String id;

    public ConsultaPreAnaliseBusca(String id) { this.id = id; }
    public ConsultaPreAnaliseBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.CONSULTA_PRE_ANALISE + Endpoint.BUSCAR;
        return Endpoint.CONSULTA_PRE_ANALISE + "/" + id + Endpoint.BUSCAR;
    }
}
