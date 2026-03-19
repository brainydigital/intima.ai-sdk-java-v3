package ai.intima.Servicos.Buscar.Credito;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class CreditoSaldoBusca implements ServicoBusca {
    @Override
    public String getEndpoint() { return Endpoint.CREDITO + Endpoint.SALDO; }
}
