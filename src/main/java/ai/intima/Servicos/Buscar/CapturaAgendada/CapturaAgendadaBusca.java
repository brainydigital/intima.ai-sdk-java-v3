package ai.intima.Servicos.Buscar.CapturaAgendada;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class CapturaAgendadaBusca implements ServicoBusca {
    private final String id;

    public CapturaAgendadaBusca(String id) { this.id = id; }
    public CapturaAgendadaBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.CAPTURA_AGENDADA + Endpoint.BUSCAR;
        return Endpoint.CAPTURA_AGENDADA + "/" + id + Endpoint.BUSCAR;
    }
}
