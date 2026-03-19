package ai.intima.Servicos.Buscar.Informacao;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class InformacaoBusca implements ServicoBusca {
    private final String id;

    public InformacaoBusca(String id) { this.id = id; }
    public InformacaoBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.INFORMACAO + Endpoint.BUSCAR;
        return Endpoint.INFORMACAO + "/" + id + Endpoint.BUSCAR;
    }
}
