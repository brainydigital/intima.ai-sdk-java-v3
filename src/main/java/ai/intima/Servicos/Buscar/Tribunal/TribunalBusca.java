package ai.intima.Servicos.Buscar.Tribunal;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class TribunalBusca implements ServicoBusca {
    private final String id;

    public TribunalBusca() {
        this.id = null;
    }

    public TribunalBusca(String id) {
        this.id = id;
    }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) {
            return Endpoint.TRIBUNAL + Endpoint.BUSCAR;
        }
        return Endpoint.TRIBUNAL + "/" + id + Endpoint.BUSCAR;
    }
}
