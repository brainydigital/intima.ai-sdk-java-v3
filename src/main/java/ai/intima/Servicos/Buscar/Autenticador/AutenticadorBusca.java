package ai.intima.Servicos.Buscar.Autenticador;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class AutenticadorBusca implements ServicoBusca {
    private final String id;

    public AutenticadorBusca(String id) { this.id = id; }
    public AutenticadorBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.AUTENTICADOR + Endpoint.BUSCAR;
        return Endpoint.AUTENTICADOR + "/" + id + Endpoint.BUSCAR;
    }
}
