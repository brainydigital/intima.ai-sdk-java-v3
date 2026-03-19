package ai.intima.Servicos.Resultado.Consulta;

import ai.intima.Servicos.Resultado.ServicoResultado;
import ai.intima.Servicos.Endpoint;

public class ConsultaResultado implements ServicoResultado {
    private final String id;

    public ConsultaResultado(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.CONSULTA + "/" + id + Endpoint.RESULTADO; }
}
