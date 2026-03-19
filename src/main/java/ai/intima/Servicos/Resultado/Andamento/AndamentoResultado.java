package ai.intima.Servicos.Resultado.Andamento;

import ai.intima.Servicos.Resultado.ServicoResultado;
import ai.intima.Servicos.Endpoint;

public class AndamentoResultado implements ServicoResultado {
    private final String id;
    private final String idProcesso;

    public AndamentoResultado(String id, String idProcesso) {
        this.id = id;
        this.idProcesso = idProcesso;
    }

    public AndamentoResultado(String id) { this(id, null); }

    @Override
    public String getEndpoint() {
        if (idProcesso == null || idProcesso.isEmpty())
            return Endpoint.ANDAMENTO + "/" + id + Endpoint.RESULTADO;
        return Endpoint.ANDAMENTO + "/" + id + Endpoint.RESULTADO + "/" + idProcesso;
    }
}
