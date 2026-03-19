package ai.intima.Servicos.Solicitar.Andamento;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;

public class AndamentoSolicitacao implements ServicoSolicitacao {
    private final String id;

    public AndamentoSolicitacao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.ANDAMENTO + "/" + id + Endpoint.SOLICITAR; }

    @Override
    public Map<String, Object> getRequest() { return new HashMap<>(); }
}
