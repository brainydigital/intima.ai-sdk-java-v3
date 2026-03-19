package ai.intima.Servicos.Resultado.Acao;

import ai.intima.Servicos.Resultado.ServicoResultado;
import ai.intima.Servicos.Endpoint;

public class AcaoResultado implements ServicoResultado {
    private final String id;

    public AcaoResultado(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.ACAO + "/" + id + Endpoint.RESULTADO; }
}
