package ai.intima.Servicos.Buscar.Transacao;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class TransacaoHistoricoBusca implements ServicoBusca {
    @Override
    public String getEndpoint() { return Endpoint.TRANSACAO + Endpoint.HISTORICO; }
}
