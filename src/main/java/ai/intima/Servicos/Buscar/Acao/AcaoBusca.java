package ai.intima.Servicos.Buscar.Acao;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class AcaoBusca implements ServicoBusca {
    private final String id;

    public AcaoBusca(String id) { this.id = id; }
    public AcaoBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.ACAO + Endpoint.BUSCAR;
        return Endpoint.ACAO + "/" + id + Endpoint.BUSCAR;
    }
}
