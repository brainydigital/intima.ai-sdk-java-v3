package ai.intima.Servicos.Buscar.CodigoAjuda;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class CodigoAjudaBusca implements ServicoBusca {
    @Override
    public String getEndpoint() { return Endpoint.CODIGO_AJUDA + Endpoint.BUSCAR; }
}
