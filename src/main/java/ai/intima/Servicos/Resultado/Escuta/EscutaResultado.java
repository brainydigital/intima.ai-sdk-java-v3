package ai.intima.Servicos.Resultado.Escuta;

import ai.intima.Servicos.Resultado.ServicoResultado;
import ai.intima.Servicos.Endpoint;

public class EscutaResultado implements ServicoResultado {
    private final String id;

    public EscutaResultado(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.ESCUTA + "/" + id + Endpoint.RESULTADO; }
}
