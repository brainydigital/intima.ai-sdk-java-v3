package ai.intima.Servicos.Buscar.Credito;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class CreditoConsumoMensalBusca implements ServicoBusca {
    @Override
    public String getEndpoint() { return Endpoint.CREDITO + Endpoint.CONSUMO_MENSAL; }
}
