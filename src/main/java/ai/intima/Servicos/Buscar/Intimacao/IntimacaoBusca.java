package ai.intima.Servicos.Buscar.Intimacao;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class IntimacaoBusca implements ServicoBusca {
    private final String id;

    public IntimacaoBusca(String id) { this.id = id; }
    public IntimacaoBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.INTIMACAO + Endpoint.BUSCAR;
        return Endpoint.INTIMACAO + "/" + id + Endpoint.BUSCAR;
    }
}
